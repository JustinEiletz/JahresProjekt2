package daos;

import objects.Tenant;
import org.hibernate.query.Query;

import java.util.List;

public class TenantDao extends BaseDao<Tenant> {
    @Override
    protected Class<Tenant> getClassType() { return Tenant.class; }

    public Tenant findById(final int tenantId) {
        Query<Tenant> tenantQuery = this.createNamedQuery("Tenant.findById");
        return tenantQuery.getSingleResult();
    }

    public List<Tenant> findAll() {
        Query<Tenant> tenantQuery = this.createNamedQuery("Tenant.findById");
        return tenantQuery.getResultList();
    }
}
