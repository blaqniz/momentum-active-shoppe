package za.co.shoppe.active.momentum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.shoppe.active.momentum.exception.CustomerManagerEnum;
import za.co.shoppe.active.momentum.exception.CustomerManagerException;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.service.CustomerService;
import za.co.shoppe.active.momentum.service.ProductService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/")
@RestController
public class StoreManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreManagerController.class);

    private ProductService productService;

    private CustomerService customerService;

    @Autowired
    public StoreManagerController(@Qualifier("ProductServiceImpl") final ProductService productService,
                                  @Qualifier("CustomerServiceImpl") final CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> listAllProducts() {
        return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
    }

    @PutMapping("products/{customerId}/{productId}/{quantity}")
    public ResponseEntity<String> purchaseProduct(@PathVariable @NotNull final Long customerId,
                                                  @PathVariable @NotNull final String productId,
                                                  @PathVariable @NotNull final int quantity) {
        try {
            return new ResponseEntity<>(productService.purchaseProduct(customerId, productId, quantity), HttpStatus.OK);
        } catch (CustomerManagerException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @PutMapping("products/{customerId}/{productIds}")
    public ResponseEntity<String> purchaseProducts(@PathVariable @NotNull final Long customerId,
                                                   @PathVariable @NotNull final String... productIds) {
        try {
            return new ResponseEntity<>(productService.purchaseProducts(customerId, productIds), HttpStatus.OK);
        } catch (CustomerManagerException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

}
