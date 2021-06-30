package sample;

import daos.UserDao;
import daos.WorkingPeriodDao;
import entity.EmployeeTableView;
import entity.User;
import entity.WorkingPeriod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import manager.ViewManager;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeeAdministrationController extends BaseController<EmployeeAdministrationController> implements Initializable {

    private final UserDao userDao = new UserDao();
    private final WorkingPeriodDao workingPeriodDao = new WorkingPeriodDao();
    private final ObservableList<EmployeeTableView> observableList = FXCollections.observableArrayList();

    @FXML
    private TableView<EmployeeTableView> userTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { setupEmployeeTableView(); }

    @Override
    protected Class<EmployeeAdministrationController> getClassType() { return EmployeeAdministrationController.class; }

    private void setupEmployeeTableView() {
        List<User> users = userDao.findAll();
        AtomicReference<EmployeeTableView> eTV = new AtomicReference<>(new EmployeeTableView());
        TableColumn<EmployeeTableView, String> id = new TableColumn<>(eTV.get().getUserId());
        TableColumn<EmployeeTableView, String> loginName = new TableColumn<>(eTV.get().getUserLoginName());
        TableColumn<EmployeeTableView, String> workingHours = new TableColumn<>(eTV.get().getUserWorkingHours());
        TableColumn<EmployeeTableView, String> workingDay = new TableColumn<>(eTV.get().getUserWorkingDay());
        userTV.getColumns().addAll(id, loginName, workingHours, workingDay);
        id.setCellValueFactory(new PropertyValueFactory<>(eTV.get().getUserId()));
        id.setMinWidth(275);

        loginName.setCellValueFactory(new PropertyValueFactory<>(eTV.get().getUserLoginName()));
        loginName.setMinWidth(275);

        workingHours.setCellValueFactory(new PropertyValueFactory<>(eTV.get().getUserWorkingHours()));
        workingHours.setMinWidth(275);

        workingDay.setCellValueFactory(new PropertyValueFactory<>(eTV.get().getUserWorkingDay()));
        workingDay.setMinWidth(275);
        for (User user : users) {
            HashMap<Date, Double> workTimes = calculateWorkingHours(user.getId());
                    workTimes.forEach((key, value) -> {
                        System.out.println(key + " : " + value);
                        eTV.set(new EmployeeTableView());
                        eTV.get().setId(user.getId());
                        eTV.get().setLoginName(user.getLoginName());
                        eTV.get().setWorkingHours(value);
                        eTV.get().setWorkingDay(key);
                        observableList.add(eTV.get());
                    });
                userTV.getItems().setAll(observableList);
        }
    }

    @FXML
    private void linkUserAdministration() { ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getUserAdministrationScene()); }

    private HashMap<Date, Double> calculateWorkingHours(final Integer userId) {
        HashMap<Date, Double> workTimes = new HashMap<>();
        List<WorkingPeriod> workingPeriodsOfUser = workingPeriodDao.findByUserId(userId);
        for (WorkingPeriod period : workingPeriodsOfUser) {
            Date start = period.getStartedWorking();
            Date end = period.getStoppedWorking();
            long diff = end.getTime() - start.getTime();
            workTimes.put(start, (double) diff / (60 * 60 * 1000));
        }
        return workTimes;
    }

}
