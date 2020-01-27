package za.co.shoppe.active.momentum.dao_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shoppe.active.momentum.model.entity.Customer;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Repository("CustomerDao")
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
