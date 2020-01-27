package za.co.shoppe.active.momentum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.shoppe.active.momentum.dao.CustomerDao;
import za.co.shoppe.active.momentum.dao.ProductDao;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    private ProductDao productDao;

    @Autowired
    public CustomerServiceImpl(@Qualifier("CustomerDao") final CustomerDao customerDao,
                               @Qualifier("ProductDao") final ProductDao productDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    @Override
    public void purchaseProducts(Long customerId, String... productCodes) {

    }
}
