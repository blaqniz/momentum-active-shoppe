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
    public Customer domainToEntity(CustomerDto skillDto) {
        final Customer skill = new Customer();
        BeanUtils.copyProperties(skillDto, skill);
        return skill;
    }

    @Override
    public CustomerDto entityToDomain(Customer skill) {
        final CustomerDto skillDto = new CustomerDto();
        BeanUtils.copyProperties(skill, skillDto);
        return skillDto;
    }
}
