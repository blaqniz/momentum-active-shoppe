package za.co.shoppe.active.momentum.dao_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shoppe.active.momentum.model.entity.Product;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Repository("ProductDao")
public interface ProductDao extends JpaRepository<Product, String> {
}
