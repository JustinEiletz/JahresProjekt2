package daos;

import entity.Tenant;
import org.hibernate.query.Query;

import java.util.List;

public class TenantDao extends BaseDao<Tenant> {

    @Override
    protected Class<Tenant> getClassType() { return Tenant.class; }

    public Tenant findById(final Integer tenantId) {
        Query<Tenant> tenantQuery = this.createNamedQuery("Tenant.findById");
        tenantQuery.setParameter("Id", tenantId);
        return tenantQuery.getSingleResult();
    }

    public List<Tenant> findAll() {
        Query<Tenant> tenantQuery = this.createNamedQuery("Tenant.findAll");
        return tenantQuery.getResultList();
    }
}
