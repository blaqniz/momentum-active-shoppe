package za.co.shoppe.active.momentum.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private String code;

    private String name;

    private BigDecimal pointsCost;

}
