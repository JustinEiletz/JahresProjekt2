package manager;

import entity.Address;
import entity.Document;
import entity.Chat;
import entity.Rental;
import entity.Tenant;
import entity.User;
import entity.WorkingPeriod;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {

    private static SessionFactoryManager instance = null;
    private final SessionFactory sF;

    private SessionFactoryManager() {
        sF = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Chat.class)
                .addAnnotatedClass(Document.class)
                .addAnnotatedClass(Tenant.class)
                .addAnnotatedClass(WorkingPeriod.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();
    }

    public static SessionFactoryManager getInstance() {
        if (instance == null)
            instance = new SessionFactoryManager();
        return instance;
    }

    public SessionFactory getSessionFactory() { return sF; }
}
