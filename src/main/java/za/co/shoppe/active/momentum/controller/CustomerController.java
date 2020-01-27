package za.co.shoppe.active.momentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.shoppe.active.momentum.service.CustomerService;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@RequestMapping("/api/customers/")
@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(@RequestBody final CustomerService customerService) {
        this.customerService = customerService;
    }

}
