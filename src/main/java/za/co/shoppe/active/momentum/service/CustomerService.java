package za.co.shoppe.active.momentum.service;

public interface CustomerService {

    void purchaseProducts(Long customerId, String... productCodes);

}
