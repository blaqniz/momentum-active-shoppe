package za.co.shoppe.active.momentum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String code;

    private String name;

    private BigDecimal pointsCost;

}
