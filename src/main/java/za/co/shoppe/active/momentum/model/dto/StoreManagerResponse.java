package za.co.shoppe.active.momentum.model.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Data
public class StoreManagerResponse<T>  {

    private T response;

    private HttpStatus httpStatus;

    public StoreManagerResponse(T response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }
}
