package za.co.shoppe.active.momentum.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "points_cost")
    private BigDecimal pointsCost;

}
