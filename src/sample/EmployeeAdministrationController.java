package sample;

import daos.UserDao;
import daos.WorkingPeriodDao;
import entity.EmployeeTableView;
import entity.User;
import entity.WorkingPeriod;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import manager.ViewManager;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeAdministrationController extends BaseController<EmployeeAdministrationController> implements Initializable {

    private final UserDao userDao = new UserDao();
    private final WorkingPeriodDao workingPeriodDao = new WorkingPeriodDao();

    @FXML
    private TableView<EmployeeTableView> userTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { setupEmployeeTableView(); }

    @Override
    protected Class<EmployeeAdministrationController> getClassType() { return EmployeeAdministrationController.class; }

    @FXML
    private void linkUserAdministration() { ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getUserAdministrationScene()); }

    private void setupEmployeeTableView() {
        List<User> users = userDao.findAll();
        EmployeeTableView eTV = new EmployeeTableView();
        TableColumn<EmployeeTableView, String> id = new TableColumn<>(eTV.getUserId());
        TableColumn<EmployeeTableView, String> loginName = new TableColumn<>(eTV.getUserLoginName());
        TableColumn<EmployeeTableView, String> workingHours = new TableColumn<>(eTV.getUserWorkingHours());
        TableColumn<EmployeeTableView, String> workingDay = new TableColumn<>(eTV.getUserWorkingDay());
        userTV.getColumns().addAll(id, loginName, workingHours, workingDay);

        id.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserId()));
        id.setMinWidth(275);

        loginName.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserLoginName()));
        loginName.setMinWidth(275);

        workingHours.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserWorkingHours()));
        workingHours.setMinWidth(275);

        workingDay.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserWorkingDay()));
        workingDay.setMinWidth(275);

        for (User user : users) {
            HashMap<Date, Double> workTimes;
            workTimes = calculateWorkingHours(user);
            eTV.setId(user.getId());
            eTV.setLoginName(user.getLoginName());
            if (workTimes == null) {
                eTV.setWorkingHours(0D);
                eTV.setWorkingDay(null);
            } else {
                eTV.setWorkingHours(workTimes.values().stream().findFirst().orElse(0D));
                eTV.setWorkingDay(workTimes.keySet().stream().findFirst().orElse(null));
            }
            userTV.getItems().add(eTV);
        }
    }

    private HashMap<Date, Double> calculateWorkingHours(final User user) {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDateTime lDTBeginOfWeek = now.with(fieldISO, 1).atStartOfDay();
        LocalDateTime lDTEndOfWeek = now.with(fieldISO, 7).atStartOfDay();
        List<WorkingPeriod> workingPeriodsOfUser = workingPeriodDao.findByUserId(user.getId());
        List<WorkingPeriod> actualWorkingPeriodOfUser = workingPeriodsOfUser.stream().filter(workingPeriodStart -> workingPeriodStart.getStartedWorking().after(convertLocalDateTime(lDTBeginOfWeek)) && workingPeriodStart.getStoppedWorking().before(convertLocalDateTime(lDTEndOfWeek))).collect(Collectors.toList());
        if (actualWorkingPeriodOfUser.size() > 0) {
            for (WorkingPeriod period : actualWorkingPeriodOfUser) {
                Date start = period.getStartedWorking();
                Date end = period.getStoppedWorking();
                long diff = end.getTime() - start.getTime();
                HashMap<Date, Double> workTimes = new HashMap<>();
                workTimes.put(start, (double) diff / (60 * 60 * 1000));
                return workTimes;
            }
        }
        System.out.println("No Entry found");
        return null;
    }

    private Date convertLocalDateTime(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
