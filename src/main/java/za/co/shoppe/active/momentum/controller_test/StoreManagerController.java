package za.co.shoppe.active.momentum.controller_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.shoppe.active.momentum.exception.CustomerIdNotFoundException;
import za.co.shoppe.active.momentum.exception.InsufficientPointsException;
import za.co.shoppe.active.momentum.exception.NoProductsCodesProvidedException;
import za.co.shoppe.active.momentum.exception.ProductCodeNotFoundException;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.model.dto.StoreManagerResponse;
import za.co.shoppe.active.momentum.service_test.ProductService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@RequestMapping("/api/")
@RestController
public class StoreManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreManagerController.class);

    private ProductService productService;

    @Autowired
    public StoreManagerController(@Qualifier("ProductServiceImpl") final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> listAllProducts() {
        return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
    }

    @PutMapping("products/{customerId}/{productId}/{quantity}")
    public ResponseEntity<Object> purchaseProduct(@PathVariable @NotNull final Long customerId,
                                                  @PathVariable @NotNull final String productId,
                                                  @PathVariable @NotNull final int quantity) {
        try {
            return new ResponseEntity(productService.purchaseProduct(customerId, productId, quantity), HttpStatus.OK);
        } catch (CustomerIdNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (InsufficientPointsException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        } catch (ProductCodeNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping("products/{customerId}/{productIds}")
    public StoreManagerResponse<Object> purchaseProducts(@PathVariable @NotNull final Long customerId,
                                                        @PathVariable @NotNull final String... productIds) {
        try {
            return new StoreManagerResponse<>(productService.purchaseProducts(customerId, productIds), HttpStatus.OK);
        } catch (CustomerIdNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new StoreManagerResponse<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (InsufficientPointsException e) {
            LOGGER.error(e.getMessage());
            return new StoreManagerResponse<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        } catch (ProductCodeNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new StoreManagerResponse<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (NoProductsCodesProvidedException e) {
            LOGGER.error(e.getMessage());
            return new StoreManagerResponse<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }

    }

}
