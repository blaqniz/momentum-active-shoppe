package za.co.shoppe.active.momentum.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @Column(name = "id")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "points_cost")
    private BigDecimal pointsCost;

}
