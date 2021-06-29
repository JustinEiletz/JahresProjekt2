package manager;

import daos.DocumentDao;
import daos.NoteDao;
import daos.RentalDao;
import daos.TenantDao;
import daos.UserDao;
import daos.WorkingPeriodDao;
import entity.Address;
import entity.Document;
import entity.Note;
import entity.Rental;
import entity.Tenant;
import entity.User;
import entity.WorkingPeriod;
import enums.RentalTyp;
import enums.UserRole;
import org.hibernate.SessionFactory;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class ApplicationManager {

    private final UserDao userDao = new UserDao();
    private final DocumentDao documentDao = new DocumentDao();
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
        testUser.setEmail("test@gmail.com");
        testUser.setHashedPassword("test");
        testUser.setLoginName("TestUser");
        testUser.setRole(UserRole.USER);
        userDao.create(testUser);

        User adminUser = new User();
        adminUser.setEmail("admin@gmail.com");
        adminUser.setHashedPassword("admin");
        adminUser.setLoginName("TestAdmin");
        adminUser.setRole(UserRole.ADMIN);
        userDao.create(adminUser);

        User guestUser = new User();
        guestUser.setEmail("guest@gmail.com");
        guestUser.setHashedPassword("");
        guestUser.setLoginName("TestGuest");
        guestUser.setRole(UserRole.GUEST);
        userDao.create(guestUser);

        Document testDoc = new Document();
        testDoc.setUser(testUser);
        testDoc.setData("Hello World - Hello lovely World!".getBytes(StandardCharsets.UTF_8));
        testDoc.setFilename("HelloWorld.txt");
        documentDao.create(testDoc);

        Document testDocV2 = new Document();
        testDocV2.setUser(testUser);
        testDocV2.setData("Hello Alien - Hello lovely Alien!".getBytes(StandardCharsets.UTF_8));
        testDocV2.setFilename("HelloAlien.txt");
        testDocV2.setPreviousVersion(testDoc);
        testDoc.setNextVersion(testDocV2);
        documentDao.create(testDocV2);
        documentDao.update(testDoc);

        RentalDao rentalDao = new RentalDao();
        TenantDao tenantDao = new TenantDao();
        Tenant tenant = new Tenant("Peter", "Pan", "0157701448");
        Address address2 = new Address("Mausestraße", "23", "Nordenham", "45435");
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
        for (int i=0; i < 20; ++i) {
            if (rng.nextInt() % 2 == 0) continue;
            WorkingPeriod period = new WorkingPeriod();
            period.setUser(adminUser);
            Date start = java.sql.Timestamp.valueOf(LocalDate.now().plusDays(i).atTime(7 + rng.nextInt(2), 0));
            Date end = java.sql.Timestamp.valueOf(LocalDate.now().plusDays(i).atTime(16 + rng.nextInt(1), 0));
            period.setStartedWorking(start);
            period.setStoppedWorking(end);
            adminUser.getWorkingPeriod().add(period);
            workDao.create(period);
        }

        NoteDao noteDao = new NoteDao();
        Note testNote = new Note();
        testNote.setUser(testUser);
        testNote.setTitle("test note");
        testNote.setDate(new Date());
        StringBuilder noteBuilder = new StringBuilder();
        for(int i=1; i < 256; i++) {
            noteBuilder.append((char)('a' + ((i + rng.nextInt(26)) % 26)));
            if(i % 25 == 0) noteBuilder.append('\n');
        }
        testNote.setContent(noteBuilder.toString());
        noteDao.create(testNote);
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
