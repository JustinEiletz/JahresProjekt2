package manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {

    private static SessionFactoryManager instance = null;
    private final SessionFactory sF;

    private SessionFactoryManager() {

        sF = new Configuration()
                .addAnnotatedClass(entity.Document.class)
                .buildSessionFactory();
    }

    public static SessionFactoryManager getInstance() {
        if (instance == null)
            instance = new SessionFactoryManager();
        return instance;
    }

    public SessionFactory getSessionFactory() { return sF; }
}
