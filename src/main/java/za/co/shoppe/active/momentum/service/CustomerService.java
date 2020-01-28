package za.co.shoppe.active.momentum.service;

import za.co.shoppe.active.momentum.model.dto.CustomerDto;

import java.util.List;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public interface CustomerService {
    List<CustomerDto> listAllCustomers();

    CustomerDto findCustomer(Long customerId);
}
