package manager;

import org.hibernate.Session;

public class SessionManager {

    private static final ThreadLocal<Session> sessionManager = new InheritableThreadLocal<>();

    private SessionManager() {}

    public static void setSessionManager(final Session session) { sessionManager.set(session); }

    public static Session getSession() { return sessionManager.get(); }

    public static void removeSession() {
        sessionManager.get()
                      .close();
        sessionManager.remove();
    }

}
