package za.co.shoppe.active.momentum.service_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.shoppe.active.momentum.dao_test.CustomerDao;
import za.co.shoppe.active.momentum.dao_test.ProductDao;
import za.co.shoppe.active.momentum.exception.*;
import za.co.shoppe.active.momentum.model.dto.CustomerDto;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.model.mapper.CustomerMapper;
import za.co.shoppe.active.momentum.model.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private ProductMapper productMapper;

    private CustomerMapper customerMapper;

    private CustomerDao customerDao;

    public ProductServiceImpl(@Qualifier("ProductDao") final ProductDao productDao,
                              @Qualifier("CustomerDao") final CustomerDao customerDao,
                              @Qualifier("ProductMapper") final ProductMapper productMapper,
                              @Qualifier("CustomerMapper") final CustomerMapper customerMapper) {
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<ProductDto> listAllProducts() {
        final var productEntities = productDao.findAll();
        return productEntities.stream().map(product -> productMapper.entityToDomain(product))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto purchaseProduct(Long customerId, String productId, int quantity) throws InsufficientPointsException,
            ProductCodeNotFoundException, CustomerIdNotFoundException {
        final var customerEntity = customerDao.findById(customerId);
        final var productEntity = productDao.findById(productId);
        if (customerEntity.isPresent()) {
            if (productEntity.isPresent()) {
                final var customer = customerEntity.get();
                final var product = productEntity.get();
                final var qty = BigDecimal.valueOf(quantity);
                if (customer.getActiveDaysPoints().compareTo(product.getPointsCost().multiply(qty)) >= 0) {
                    customer.setActiveDaysPoints(customer.getActiveDaysPoints().subtract(product.getPointsCost().multiply(qty)));
                    return customerMapper.entityToDomain(customerDao.save(customer));
                } else {
                    throw new InsufficientPointsException(CustomerManagerEnum.NO_ENOUGH_POINTS.getStatusDescription());
                }
            } else {
                throw new ProductCodeNotFoundException(CustomerManagerEnum.NO_SUCH_PRODUCT_CODE.getStatusDescription());
            }
        } else {
            throw new CustomerIdNotFoundException(CustomerManagerEnum.CUSTOMER_ID_DOES_NOT_EXIST.getStatusDescription());
        }
    }

    @Override
    public CustomerDto purchaseProducts(final Long customerId, final String... productIds) throws InsufficientPointsException,
                ProductCodeNotFoundException, CustomerIdNotFoundException, NoProductsCodesProvidedException {
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
                            throw new InsufficientPointsException(CustomerManagerEnum.NO_ENOUGH_POINTS.getStatusDescription());
                        }
                    } else {
                        throw new ProductCodeNotFoundException(CustomerManagerEnum.NO_SUCH_PRODUCT_CODE.getStatusDescription());
                    }
                }
                customerDao.save(customer);
            } else {
                throw new NoProductsCodesProvidedException(CustomerManagerEnum.NO_PRODUCTS_SPECIFIED.getStatusDescription());
            }
            return customerMapper.entityToDomain(customerEntity.get());
        } else {
            throw new CustomerIdNotFoundException(CustomerManagerEnum.CUSTOMER_ID_DOES_NOT_EXIST.getStatusDescription());
        }
    }
}
