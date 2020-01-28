package za.co.shoppe.active.momentum.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.shoppe.active.momentum.dao.CustomerDaoTest;
import za.co.shoppe.active.momentum.dao.ProductDao;
import za.co.shoppe.active.momentum.dao.ProductDaoTest;
import za.co.shoppe.active.momentum.exception.CustomerIdNotFoundException;
import za.co.shoppe.active.momentum.exception.InsufficientPointsException;
import za.co.shoppe.active.momentum.exception.NoProductsCodesProvidedException;
import za.co.shoppe.active.momentum.exception.ProductCodeNotFoundException;
import za.co.shoppe.active.momentum.model.dto.CustomerDto;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.model.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductService productService;

    @Before
    public void setup() {
        final var product = ProductDaoTest.getProduct();
        when(productDao.save(product)).thenReturn(product);
        productDao.save(product);
    }

    @Test
    public void when_ListAllThenReturnAllProducts() {
        final var products = productDao.findAll();
        final List<ProductDto> productDTOs = products.stream().map(product -> productMapper.entityToDomain(product)).collect(Collectors.toList());
        given(productService.listAllProducts()).willReturn(productDTOs);
        assertThat(productService.listAllProducts()).isEqualTo(productDTOs);
    }

    @Test
    public void when_PurchaseProductSubtractEmployeePointsOnSuccess() throws InsufficientPointsException, CustomerIdNotFoundException, ProductCodeNotFoundException {
        final var product = ProductDaoTest.getProduct();
        final var customer = CustomerDaoTest.getCustomer();

        var returnedCustomer = getCustomer();
        given(productService.purchaseProduct(customer.getId(), product.getCode(), 2)).willReturn(returnedCustomer);
        assertThat(productService.purchaseProduct(customer.getId(), product.getCode(), 2).getActiveDaysPoints())
                .isEqualTo(customer.getActiveDaysPoints().subtract(BigDecimal.valueOf(90)));

    }

    @Test
    public void when_PurchaseProductsSubtractEmployeePointsOnSuccess() throws InsufficientPointsException, CustomerIdNotFoundException, ProductCodeNotFoundException, NoProductsCodesProvidedException {
        final var products = getProducts();
        final var customer = CustomerDaoTest.getCustomer();

        var returnedCustomer = getCustomerWithMultipleProducts();
        final Object[] productCodes = products.stream().map(p -> p.getCode()).collect(Collectors.toList()).toArray();
        given(productService.purchaseProducts(customer.getId(), (String) productCodes[0], (String) productCodes[1], (String) productCodes[2])).willReturn(returnedCustomer);
        assertThat(productService.purchaseProducts(customer.getId(), (String) productCodes[0], (String) productCodes[1], (String) productCodes[2]).getActiveDaysPoints())
                .isEqualTo(customer.getActiveDaysPoints().subtract(BigDecimal.valueOf(95)));

    }

    private CustomerDto getCustomer() {
        final var customer = new CustomerDto();
        customer.setActiveDaysPoints(BigDecimal.valueOf(60));
        customer.setName("Sydney");
        return customer;
    }

    private CustomerDto getCustomerWithMultipleProducts() {
        final var customer = new CustomerDto();
        customer.setActiveDaysPoints(BigDecimal.valueOf(55));
        customer.setName("Sydney");
        return customer;
    }

    private List<ProductDto> getProducts() {
        final var products = new ArrayList<ProductDto>();
        final var usb = new ProductDto("USB", "USB Flash Drive 8 GB", BigDecimal.valueOf(45));
        final var mouse = new ProductDto("MOUSE", "Manhattan Mouse", BigDecimal.valueOf(30));
        final var keyboard = new ProductDto("USB", "USB Flash Drive 8 GB", BigDecimal.valueOf(20));
        products.add(usb);
        products.add(mouse);
        products.add(keyboard);
        return products;
    }

}
