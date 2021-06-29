package manager;

import daos.*;
import entity.*;
import enums.RentalTyp;
import enums.UserRole;
import org.hibernate.SessionFactory;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class ApplicationManager {

    private static ApplicationManager context;
    private static User currentUser;

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
        testUser.setRole(UserRole.USER);
        testUser.setLoginName("User");

        User adminUser = new User();
        adminUser.setEmail("admin@mail.de");
        adminUser.setHashedPassword("admin");
        adminUser.setRole(UserRole.ADMIN);
        adminUser.setLoginName("Admin");

        User guestUser = new User();
        guestUser.setEmail("guest@mail.de");
        guestUser.setHashedPassword("");
        guestUser.setRole(UserRole.GUEST);
        guestUser.setLoginName("Guest");

        UserDao userDao = new UserDao();
        userDao.create(testUser);
        userDao.create(adminUser);
        userDao.create(guestUser);

        DocumentDao documentDao = new DocumentDao();
        Document testDoc = new Document();
        Document testDocV2 = new Document();

        testDoc.setUser(testUser);
        testDoc.setData("Hello World".getBytes(StandardCharsets.UTF_8));
        testDoc.setFilename("Hello.txt");
        documentDao.create(testDoc);
        testDocV2.setUser(testUser);
        testDocV2.setData("Hello World!".getBytes(StandardCharsets.UTF_8));
        testDocV2.setFilename("Hello_v2.txt");
        testDocV2.setPreviousVersion(testDoc);
        documentDao.create(testDocV2);
        testDoc.setNextVersion(testDocV2);
        documentDao.update(testDoc);

        RentalDao rentalDao = new RentalDao();
        TenantDao tenantDao = new TenantDao();


        Tenant tenant = new Tenant("Peter", "Pan", "432484092");
        Address address2 = new Address("12", "12", "12", "12");
        tenant.setAddress(address2);
        tenantDao.create(tenant);

        Rental rental = new Rental();
        Address address = new Address("1", "1", "1", "1");
        rental.setAddress(address);
        rental.setAdditionalCosts(20.0);
        rental.setNotice("Notice");
        rental.setLivingSpace(20.0);
        rental.setObjectDesc("Desc");
        rental.setObjectTyp(RentalTyp.PRIVATE.name());
        rental.setObjectNr(1);
        rental.setPriceSquareMeterCold(20.0);
        rental.setTenant(tenant);
        rentalDao.create(rental);

        Random rng = new Random(123);
        WorkingPeriodDao workDao = new WorkingPeriodDao();
        for(int i=0; i < 20; ++i) {
            WorkingPeriod period = new WorkingPeriod();
            period.setUser(testUser);
            Date start = java.sql.Timestamp.valueOf(LocalDate.now().plusDays(i).atTime(7 + rng.nextInt(2), 0));
            Date end = java.sql.Timestamp.valueOf(LocalDate.now().plusDays(i).atTime(16 + rng.nextInt(1), 0));
            period.setStartedWorking(start);
            period.setStoppedWorking(end);
            workDao.create(period);
        }
    }

    public static ApplicationManager getInstance() {
        if (context == null)
            context = new ApplicationManager();
        return context;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(final User currentUser) { ApplicationManager.currentUser = currentUser; }
}
