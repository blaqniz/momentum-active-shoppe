package za.co.shoppe.active.momentum.service;

import za.co.shoppe.active.momentum.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> listAllProducts();

    String purchaseProduct(Long customerId, String productId, int quantity);

    String purchaseProducts(Long customerId, String... productIds);

}
