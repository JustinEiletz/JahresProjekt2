package daos;

import entity.Rental;
import org.hibernate.query.Query;

import java.util.List;

public class RentalDao extends BaseDao<Rental> {

    @Override
    protected Class<Rental> getClassType() { return Rental.class; }

    public Rental findById(final Integer rentalId) {
        Query<Rental> rentalQuery = this.createNamedQuery("Rental.findById");
        rentalQuery.setParameter("Id", rentalId);
        return rentalQuery.getSingleResult();
    }

    public List<Rental> findAll() {
        Query<Rental> rentalQuery = this.createNamedQuery("Rental.findAll");
        return rentalQuery.getResultList();
    }

}
