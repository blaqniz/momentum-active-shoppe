package za.co.shoppe.active.momentum.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Data
public class CustomerDto {

    private Long id;

    private String name;

    private BigDecimal activeDaysPoints;

}
