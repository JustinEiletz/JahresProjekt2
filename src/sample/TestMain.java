package sample;

import daos.DocumentDao;
import daos.UserDao;
import daos.WorkingPeriodDao;
import entity.User;
import entity.WorkingPeriod;
import manager.SessionManager;
import org.hibernate.SessionFactory;
import manager.SessionFactoryManager;

import entity.Document;

import java.nio.file.Path;
import java.util.Date;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        SessionFactoryManager sfManager = SessionFactoryManager.getInstance();
        SessionFactory sf = sfManager.getSessionFactory();
        SessionManager.setSessionManager(sf.openSession());
        try {
            DocumentDao docDao = new DocumentDao();

            Document doc = new Document();
            byte[] data = java.nio.file.Files.readAllBytes(Path.of("C:\\Users\\chanis\\Desktop\\jahresprojekt\\JahresProjekt2\\src\\entity\\Document.java"));
            doc.setData(data);
            doc.setFilename("Document.java");

            docDao.create(doc);

            Document doc_v2 = new Document();
            doc_v2.setFilename("Document_v2.java");
            doc_v2.setData(data);
            doc_v2.setPrevious_version(doc);

            docDao.create(doc_v2);

            doc.setNext_version(doc_v2);

            docDao.update(doc);

            Document test = docDao.findById(1);
            System.out.println(test);
            test = docDao.findById(2);
            System.out.println(test);

            List<Document> documents = docDao.findAll();
            for(Document d : documents) {
                System.out.println(d);
            }

            UserDao userDao = new UserDao();

            User user1 = new User();
            user1.setEmail("admin@web.de");
            user1.setPassword("passwort");
            user1.setAdmin(true);

            userDao.create(user1);

            User user2 = new User();
            user2.setEmail("asdsd@mail.de");
            user2.setAdmin(false);
            user2.setPassword("PassWord");
            userDao.create(user2);

            List<User> users = userDao.findAll();
            for(User u : users) {
                System.out.println(u);
            }

            WorkingPeriodDao workingDao = new WorkingPeriodDao();
            WorkingPeriod entry1 = new WorkingPeriod();
            entry1.setUser(user1);
            entry1.setStartedWorking(new Date());
            workingDao.create(entry1);

            // an hour passes
            entry1.setStoppedWorking(new Date(entry1.getStartedWorking().getTime() + 1000*3600));
            workingDao.update(entry1);

            List<WorkingPeriod> entries = workingDao.findAll();
            for(WorkingPeriod p : entries) {
                System.out.println(p);
            }

        } catch(Exception e) {
            System.out.println("EXCEPTION");
            e.printStackTrace();
        }


        System.out.println("END");
        SessionManager.removeSession();
    }
}
