package daos;

import entity.WorkingPeriod;
import org.hibernate.query.Query;

import java.util.List;

public class WorkingPeriodDao extends BaseDao<WorkingPeriod> {

    @Override
    protected Class<WorkingPeriod> getClassType() {
        return WorkingPeriod.class;
    }

    public List<WorkingPeriod> findAll() {
        Query<WorkingPeriod> documentQuery = this.createNamedQuery("WorkingPeriod.findAll");
        return documentQuery.getResultList();
    }
}
