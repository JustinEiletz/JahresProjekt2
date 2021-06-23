package daos;

import manager.SessionManager;
import org.hibernate.Session;
import org.hibernate.query.Query;


public abstract class BaseDao<T> {

    protected abstract Class<T> getClassType();

    public void create(T data) {
        Session session = SessionManager.getSession();
        session.beginTransaction();
        session.save(data);
        session.getTransaction().commit();
    }

    public void update(T data) {
        Session session = SessionManager.getSession();
        session.beginTransaction();
        session.update(data);
        session.getTransaction().commit();
    }

    public void delete(T data) {
        Session session = SessionManager.getSession();
        session.beginTransaction();
        session.delete(data);
        session.getTransaction().commit();
    }

    Query<T> createNamedQuery(String queryName) {
        Session session = SessionManager.getSession();
        return session.createNamedQuery(queryName, this.getClassType());
    }
}
