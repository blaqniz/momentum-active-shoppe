package za.co.shoppe.active.momentum.dao_test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.shoppe.active.momentum.model.entity.Customer;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerDao customerDao;

    @Before
    public void setup() {
        initialize();
    }

    @Test
    public void whenFindById_thenReturnCustomerTest() {
        final var customerId = 1L;    //The first generated invoiceId value in the DB should be 1
        final var customer = customerDao.getOne(customerId);
        assertThat(customer.getId()).isEqualTo(customerId);
    }

    @Test
    public void whenAll_thenReturnAllCustomersTest() {
        final var customers = customerDao.findAll();
        assertTrue(customers.size() == 1);
    }

    private void initialize() {
        final var customer = getCustomer();
        testEntityManager.merge(customer);
    }

    public static Customer getCustomer() {
        final var customer = new Customer();
        customer.setName("Sydney");
        customer.setActiveDaysPoints(BigDecimal.valueOf(150));
        return customer;
    }

}



