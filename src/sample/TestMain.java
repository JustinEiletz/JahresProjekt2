package sample;

import daos.DocumentDao;
import manager.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import manager.SessionFactoryManager;

import entity.Document;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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


        } catch(Exception e) {
            e.printStackTrace();
        }


        System.out.println("END");
        SessionManager.removeSession();
    }
}
