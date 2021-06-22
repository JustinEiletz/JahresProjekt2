package daos;

import entity.Rental;
import entity.User;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDao extends BaseDao<User> {
    @Override
    protected Class<User> getClassType() {
        return User.class;
    }

    public List<User> findAll() {
        Query<User> rentalQuery = this.createNamedQuery("User.findAll");
        return rentalQuery.getResultList();
    }

    public User findByName(String name)
    {
        // this could be replaced with a query
        List<User> users = findAll();
        Optional<User> foundUser = users.stream().filter(user -> user.getEmail().equals(name)).findAny();
        return foundUser.orElse(null);
    }
}
