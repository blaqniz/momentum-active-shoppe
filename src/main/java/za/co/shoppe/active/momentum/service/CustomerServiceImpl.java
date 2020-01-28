package za.co.shoppe.active.momentum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.shoppe.active.momentum.dao.CustomerDao;
import za.co.shoppe.active.momentum.model.dto.CustomerDto;
import za.co.shoppe.active.momentum.model.entity.Customer;
import za.co.shoppe.active.momentum.model.mapper.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;

    private CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(@Qualifier("CustomerDao") final CustomerDao customerDao,
                               @Qualifier("CustomerMapper") final CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> listAllCustomers() {
        final List<Customer> customers = customerDao.findAll();
        return customers.stream().map(customer -> customerMapper.entityToDomain(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomer(final Long customerId) {
        return customerMapper.entityToDomain(customerDao.getOne(customerId));
    }
}
