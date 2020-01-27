package za.co.shoppe.active.momentum.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import za.co.shoppe.active.momentum.model.dto.ProductDto;
import za.co.shoppe.active.momentum.model.entity.Product;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

@Component("ProductMapper")
public class ProductMapper extends AbstractMapper<Product, ProductDto> {

    @Override
    public Product domainToEntity(ProductDto buDomain) {
        final Product buDB = new Product();
        BeanUtils.copyProperties(buDomain, buDB);
        return buDB;
    }

    @Override
    public ProductDto entityToDomain(Product buDB) {
        final ProductDto buDomain = new ProductDto();
        BeanUtils.copyProperties(buDB, buDomain);
        return buDomain;
    }
}
