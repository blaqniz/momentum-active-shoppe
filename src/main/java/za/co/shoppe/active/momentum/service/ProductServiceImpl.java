package za.co.shoppe.active.momentum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.shoppe.active.momentum.dao.CustomerDao;
import za.co.shoppe.active.momentum.dao.ProductDao;
import za.co.shoppe.active.momentum.exception.CustomerManagerEnum;
import za.co.shoppe.active.momentum.exception.CustomerManagerException;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.model.entity.Customer;
import za.co.shoppe.active.momentum.model.entity.Product;
import za.co.shoppe.active.momentum.model.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductDao productDao;

    private ProductMapper productMapper;

    private CustomerDao customerDao;

    public ProductServiceImpl(@Qualifier("ProductDao") final ProductDao productDao,
                              @Qualifier("CustomerDao") final CustomerDao customerDao,
                              @Qualifier("ProductMapper") final ProductMapper productMapper) {
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> listAllProducts() {
        final var productEntities = productDao.findAll();
        return productEntities.stream().map(product -> productMapper.entityToDomain(product))
                .collect(Collectors.toList());
    }

    @Override
    public String purchaseProduct(Long customerId, String productId, int quantity) throws CustomerManagerException {
        final var customerEntity = customerDao.findById(customerId);
        final var productEntity = productDao.findById(productId);
        if (customerEntity.isPresent()) {
            if (productEntity.isPresent()) {
                final var customer = customerEntity.get();
                final var product = productEntity.get();
                final var qty = BigDecimal.valueOf(quantity);
                if (customer.getActiveDaysPoints().compareTo(product.getPointsCost().multiply(qty)) >= 0) {
                    customer.setActiveDaysPoints(customer.getActiveDaysPoints().subtract(product.getPointsCost().multiply(qty)));
                    customerDao.save(customer);
                    return "OK";
                } else {
                    throw new CustomerManagerException(CustomerManagerEnum.NO_ENOUGH_POINTS.getStatusDescription());
                }
            } else {
                LOGGER.error("product with id {} doesn't exist!", productId);
                throw new CustomerManagerException(CustomerManagerEnum.NO_SUCH_PRODUCT_CODE.getStatusDescription());
            }
        } else {
            LOGGER.error("Customer with id {} doesn't exist!", customerId);
            throw new CustomerManagerException(CustomerManagerEnum.CUSTOMER_ID_DOES_NOT_EXIST.getStatusDescription());
        }
    }

    @Override
    public String purchaseProducts(final Long customerId, final String... productIds) throws CustomerManagerException {
        final var customerEntity = customerDao.findById(customerId);
        if (customerEntity.isPresent()) {
            final var customer = customerEntity.get();

            // Check if customer provided any productId's
            if (productIds.length > 0) {
                for (var productId : productIds) {
                    final var productEntity = productDao.findById(productId);
                    // If provided productId doesn't have a matching product in the db, throw an exception
                    if (productEntity.isPresent()) {
                        final var product = productEntity.get();
                        if (customer.getActiveDaysPoints().compareTo(product.getPointsCost()) >= 0) {
                            customer.setActiveDaysPoints(customer.getActiveDaysPoints().subtract(product.getPointsCost()));
                        } else {
                            throw new CustomerManagerException(CustomerManagerEnum.NO_ENOUGH_POINTS.getStatusDescription());
                        }
                    } else {
                        throw new CustomerManagerException(CustomerManagerEnum.NO_SUCH_PRODUCT_CODE.getStatusDescription());
                    }
                }
                customerDao.save(customer);
            } else {
                throw new CustomerManagerException(CustomerManagerEnum.NO_PRODUCTS_SPECIFIED.getStatusDescription());
            }
        } else {
            throw new CustomerManagerException(CustomerManagerEnum.CUSTOMER_ID_DOES_NOT_EXIST.getStatusDescription());
        }
        return "OK";
    }
}
