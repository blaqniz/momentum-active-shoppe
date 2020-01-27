package za.co.shoppe.active.momentum.model.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import za.co.shoppe.active.momentum.exception.CustomerManagerEnum;

public class StoreManagerResponse extends ResponseEntity {

    private CustomerManagerEnum customerManagerEnum;

    public StoreManagerResponse(HttpStatus status) {
        super(status);
    }

    public StoreManagerResponse(Object body, HttpStatus status) {
        super(body, status);
    }

    public StoreManagerResponse(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public StoreManagerResponse(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    public CustomerManagerEnum getCustomerManagerEnum() {
        return customerManagerEnum;
    }

    public void setCustomerManagerEnum(CustomerManagerEnum customerManagerEnum) {
        this.customerManagerEnum = customerManagerEnum;
    }
}
