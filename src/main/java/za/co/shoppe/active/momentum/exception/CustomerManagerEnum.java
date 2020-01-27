package za.co.shoppe.active.momentum.exception;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public enum CustomerManagerEnum {

    NO_ENOUGH_POINTS(1001, "No enough point available", 1001),
    NO_PRODUCTS_SPECIFIED(1002, "No product codes specified", 1002),
    NO_SUCH_PRODUCT_CODE(1003, "Product code specified does not exist", 1003),
    CUSTOMER_ID_DOES_NOT_EXIST(1004, "Customer id does not exist", 1004);

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
