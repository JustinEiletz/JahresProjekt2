package manager;

import daos.UserDao;
import entity.User;
import org.hibernate.SessionFactory;

public class ApplicationManager {
    private static ApplicationManager context;

    private User currentUser;

    public ApplicationManager() {
        SessionFactoryManager sfManager = SessionFactoryManager.getInstance();
        SessionFactory sf = sfManager.getSessionFactory();
        SessionManager.setSessionManager(sf.openSession());
        addTestData();
    }

    private void addTestData() {
        User testUser = new User();
        testUser.setEmail("test@mail.de");
        testUser.setHashedPassword("pass123");
        testUser.setAdmin(false);

        UserDao userDao = new UserDao();
        userDao.create(testUser);
    }

    public static ApplicationManager getInstance() {
        if (context == null)
            context = new ApplicationManager();
        return context;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
