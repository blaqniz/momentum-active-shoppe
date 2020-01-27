package za.co.shoppe.active.momentum.exception;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public enum CustomerManagerEnum {

    CUSTOMER_ID_DOES_NOT_EXIST(1001, "Customer id does not exist", 1001),
    NO_ENOUGH_POINTS(1002, "No enough point available", 1002),
    NO_PRODUCTS_SPECIFIED(1003, "No product codes specified", 1003),
    NO_SUCH_PRODUCT_CODE(1004, "Product code specified does not exist", 1004);

    final int errorCode;
    final String statusDescription;
    final int httpResponseCode;
    final boolean successStatus;

    CustomerManagerEnum(int errorCode, String statusDescription, int httpResponseCode) {
        this(errorCode, statusDescription, httpResponseCode, false);
    }

    CustomerManagerEnum(int errorCode, String statusDescription, int httpResponseCode, boolean successStatus) {
        this.errorCode = errorCode;
        this.statusDescription = statusDescription;
        this.httpResponseCode = httpResponseCode;
        this.successStatus = successStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    public boolean isSuccessStatus() {
        return successStatus;
    }
}
