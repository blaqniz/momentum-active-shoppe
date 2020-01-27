package za.co.shoppe.active.momentum.service;

import za.co.shoppe.active.momentum.exception.CustomerManagerException;
import za.co.shoppe.active.momentum.model.dto.ProductDto;

import java.util.List;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public interface ProductService {
    List<ProductDto> listAllProducts();

    String purchaseProduct(Long customerId, String productId, int quantity) throws CustomerManagerException;

    String purchaseProducts(Long customerId, String... productIds) throws CustomerManagerException;

}
