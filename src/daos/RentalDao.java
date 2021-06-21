package daos;

import entity.Rental;
import org.hibernate.query.Query;

import java.util.List;

public class RentalDao extends BaseDao<Rental> {

    @Override
    protected Class<Rental> getClassType() { return Rental.class; }

    public Rental findById(final int rentalId) {
        Query<Rental> rentalQuery = this.createNamedQuery("Rental.findById");
        return rentalQuery.getSingleResult();
    }

    public List<Rental> findAll() {
        Query<Rental> rentalQuery = this.createNamedQuery("Rental.findAll");
        return rentalQuery.getResultList();
    }

}
