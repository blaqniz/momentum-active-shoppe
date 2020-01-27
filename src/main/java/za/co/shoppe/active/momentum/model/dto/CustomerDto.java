package za.co.shoppe.active.momentum.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDto {

    private Long id;

    private String name;

    private BigDecimal activeDaysPoints;

}
