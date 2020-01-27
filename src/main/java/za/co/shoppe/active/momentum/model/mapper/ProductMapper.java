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
    public ProductDto entityToDomain(Product productEntity) {
        final ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity, productDto);
        return productDto;
    }
}
