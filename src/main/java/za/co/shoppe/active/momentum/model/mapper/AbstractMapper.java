package za.co.shoppe.active.momentum.model.mapper;

/**
 * @author Syndey
 * Date: 27/01/2020
 */

public abstract class AbstractMapper<ENTITY, DOMAIN> {

    public abstract ENTITY domainToEntity(DOMAIN domain);

    public abstract DOMAIN entityToDomain(ENTITY db);

}
