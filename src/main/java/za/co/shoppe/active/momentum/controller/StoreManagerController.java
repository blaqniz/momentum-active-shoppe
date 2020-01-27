package za.co.shoppe.active.momentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.service.CustomerService;
import za.co.shoppe.active.momentum.service.ProductService;

import java.util.List;

@RequestMapping("/api/")
@RestController
public class StoreManagerController {

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
    public ResponseEntity<String> purchaseProduct(@PathVariable final Long customerId, @PathVariable final String productId, @PathVariable final int quantity) {
        return new ResponseEntity<>(productService.purchaseProduct(customerId, productId, quantity), HttpStatus.OK);
    }

    @PutMapping("products/{customerId}/{productIds}")
    public ResponseEntity<String> purchaseProducts(@PathVariable final Long customerId, @PathVariable final String... productIds) {
        return new ResponseEntity<>(productService.purchaseProducts(customerId, productIds), HttpStatus.OK);
    }

}
