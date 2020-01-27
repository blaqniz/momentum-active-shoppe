package za.co.shoppe.active.momentum.service;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public interface CustomerService {

    void purchaseProducts(Long customerId, String... productCodes);

}
