package daos;

import entity.User;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao extends BaseDao<User> {

    @Override
    protected Class<User> getClassType() { return User.class; }

    public List<User> findAll() {
        Query<User> userQuery = this.createNamedQuery("User.findAll");
        return userQuery.getResultList();
    }

    public User findByLogin(final String login) {
        Query<User> userQuery = this.createNamedQuery("User.findByLogin");
        userQuery.setParameter("login", login);
        return userQuery.getSingleResult();
    }
}
