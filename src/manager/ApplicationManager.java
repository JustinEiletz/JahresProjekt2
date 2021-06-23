package manager;

import daos.DocumentDao;
import daos.UserDao;
import entity.Document;
import entity.User;
import org.hibernate.SessionFactory;

import java.nio.charset.StandardCharsets;

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

        DocumentDao documentDao = new DocumentDao();
        Document testDoc = new Document();
        Document testDocV2 = new Document();

        testDoc.setUser(testUser);
        testDoc.setData("Hello Wordl".getBytes(StandardCharsets.UTF_8));
        testDoc.setFilename("Hello.txt");
        documentDao.create(testDoc);
        testDocV2.setUser(testUser);
        testDocV2.setData("Hello World!".getBytes(StandardCharsets.UTF_8));
        testDocV2.setFilename("Hello_v2.txt");
        testDocV2.setPreviousVersion(testDoc);
        documentDao.create(testDocV2);
        testDoc.setNextVersion(testDocV2);
        documentDao.update(testDoc);
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
