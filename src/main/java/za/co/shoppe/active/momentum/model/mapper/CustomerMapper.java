package za.co.shoppe.active.momentum.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import za.co.shoppe.active.momentum.model.dto.CustomerDto;
import za.co.shoppe.active.momentum.model.entity.Customer;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Component("CustomerMapper")
public class CustomerMapper extends AbstractMapper<Customer, CustomerDto> {

    @Override
    public CustomerDto entityToDomain(Customer customer) {
        final CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
}
