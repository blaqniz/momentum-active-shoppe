package za.co.shoppe.active.momentum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.shoppe.active.momentum.dao.CustomerDao;
import za.co.shoppe.active.momentum.dao.ProductDao;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.model.entity.Customer;
import za.co.shoppe.active.momentum.model.entity.Product;
import za.co.shoppe.active.momentum.model.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public String purchaseProduct(Long customerId, String productId, int quantity) {
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
                }
            } else {
                LOGGER.error("product with id {} doesn't exist!", productId);
            }
        } else {
            LOGGER.error("Customer with id {} doesn't exist!", customerId);
        }
        return null;
    }

    @Override
    public String purchaseProducts(final Long customerId, final String... productIds) {
        final var customerEntity = customerDao.findById(customerId);
        if (customerEntity.isPresent()) {
            Arrays.stream(productIds).forEach(s -> {
                final var productEntity = productDao.findById(s);
                if (productEntity.isPresent()) {
                    final var customer = customerEntity.get();
                    final var product = productEntity.get();
                    if (customer.getActiveDaysPoints().compareTo(product.getPointsCost()) >= 0) {
                        customer.setActiveDaysPoints(customer.getActiveDaysPoints().subtract(product.getPointsCost()));
                        customerDao.save(customer);
                    }
                } else {
                    LOGGER.error("product with id {} doesn't exist!", s);
                }
            });
        }
        return "OK";
    }
}
