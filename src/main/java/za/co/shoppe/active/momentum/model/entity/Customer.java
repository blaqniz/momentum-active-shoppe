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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active_days_points")
    private BigDecimal activeDaysPoints;

}
