package za.co.shoppe.active.momentum.service;

import za.co.shoppe.active.momentum.exception.CustomerIdNotFoundException;
import za.co.shoppe.active.momentum.exception.InsufficientPointsException;
import za.co.shoppe.active.momentum.exception.NoProductsCodesProvidedException;
import za.co.shoppe.active.momentum.exception.ProductCodeNotFoundException;
import za.co.shoppe.active.momentum.model.dto.CustomerDto;
import za.co.shoppe.active.momentum.model.dto.ProductDto;

import java.util.List;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public interface ProductService {
    List<ProductDto> listAllProducts();

    CustomerDto purchaseProduct(Long customerId, String productId, int quantity) throws CustomerIdNotFoundException,
            InsufficientPointsException, ProductCodeNotFoundException;

    CustomerDto purchaseProducts(Long customerId, String... productIds) throws CustomerIdNotFoundException,
            InsufficientPointsException, ProductCodeNotFoundException, NoProductsCodesProvidedException;

}
