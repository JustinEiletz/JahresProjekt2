package manager;

import entity.WorkingPeriod;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {

    private static SessionFactoryManager instance = null;
    private final SessionFactory sF;

    private SessionFactoryManager() {
        sF = new Configuration()
                .addAnnotatedClass(entity.Document.class)
                .addAnnotatedClass(entity.User.class)
                .addAnnotatedClass(WorkingPeriod.class)
                .buildSessionFactory();
    }

    public static SessionFactoryManager getInstance() {
        if (instance == null)
            instance = new SessionFactoryManager();
        return instance;
    }

    public SessionFactory getSessionFactory() { return sF; }
}
