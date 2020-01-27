package za.co.shoppe.active.momentum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shoppe.active.momentum.model.entity.Product;

@Repository("ProductDao")
public interface ProductDao extends JpaRepository<Product, String> {
}
