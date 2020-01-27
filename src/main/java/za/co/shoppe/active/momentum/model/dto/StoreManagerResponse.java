package za.co.shoppe.active.momentum.model.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Data
public class StoreManagerResponse<T>  {

    private T myClass;

    private HttpStatus httpStatus;

    public StoreManagerResponse(T myClass, HttpStatus httpStatus) {
        this.myClass = myClass;
        this.httpStatus = httpStatus;
    }
}
