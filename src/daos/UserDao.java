package daos;

import entity.Rental;
import entity.User;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao extends BaseDao<User> {
    @Override
    protected Class<User> getClassType() {
        return User.class;
    }

    public List<User> findAll() {
        Query<User> rentalQuery = this.createNamedQuery("User.findAll");
        return rentalQuery.getResultList();
    }}
