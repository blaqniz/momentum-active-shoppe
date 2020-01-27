package za.co.shoppe.active.momentum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shoppe.active.momentum.model.entity.Customer;

@Repository("CustomerDao")
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
