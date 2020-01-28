package za.co.shoppe.active.momentum.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.shoppe.active.momentum.model.entity.Product;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductDao productDao;

    @Before
    public void setup() {
        initialize();
    }

    @Test
    public void when_AllThenReturnAllProductsTest() {
        testEntityManager.flush();
        final var products = productDao.findAll();
        assertTrue(products.contains(getProduct()));
    }

    @Test
    public void when_FindByProductCodeThenReturnProductTest() {
        testEntityManager.flush();
        final var product = productDao.getOne("USB");
        assertNotNull(product);
        assertTrue(product.getPointsCost().compareTo(BigDecimal.valueOf(45)) == 0);
    }

    private void initialize() {
        final var product = getProduct();
        testEntityManager.merge(product);
    }

    public static Product getProduct() {
        var product = new Product("USB", "USB Flash Drive 8 GB", BigDecimal.valueOf(45));
        return product;
    }

}
