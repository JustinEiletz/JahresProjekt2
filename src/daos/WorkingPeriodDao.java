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
        Query<WorkingPeriod> workingPeriodQuery = this.createNamedQuery("WorkingPeriod.findAll");
        return workingPeriodQuery.getResultList();
    }

    public List<WorkingPeriod> findByUserId(final Integer userId) {
        Query<WorkingPeriod> workingPeriodQuery = this.createNamedQuery("WorkingPeriod.findByUserId");
        workingPeriodQuery.setParameter("Id", userId);
        return workingPeriodQuery.getResultList();
    }
}
