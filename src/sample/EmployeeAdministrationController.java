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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeAdministrationController extends BaseController<EmployeeAdministrationController> implements Initializable {

    private final UserDao userDao = new UserDao();
    private final WorkingPeriodDao workingPeriodDao = new WorkingPeriodDao();

    @FXML
    private TableView<EmployeeTableView> userTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { setupEmployeeTableView(); }

    @Override
    protected Class<EmployeeAdministrationController> getClassType() { return EmployeeAdministrationController.class; }

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
            workTimes = calculateWorkingHours();
            for (int i = 0; i < workTimes.size(); i++) {
                eTV.setId(user.getId());
                eTV.setLoginName(user.getLoginName());
                eTV.setWorkingHours(workTimes.values().stream().toList().get(i));
                eTV.setWorkingDay(workTimes.keySet().stream().toList().get(i));
                userTV.getItems().add(eTV);
            }
        }
    }

    @FXML
    private void linkUserAdministration() { ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getUserAdministrationScene()); }

    private HashMap<Date, Double> calculateWorkingHours() {
        HashMap<Date, Double> workTimes = new HashMap<>();
        List<WorkingPeriod> workingPeriodsOfUser = workingPeriodDao.findAll();
        for (WorkingPeriod period : workingPeriodsOfUser) {
            Date start = period.getStartedWorking();
            Date end = period.getStoppedWorking();
            long diff = end.getTime() - start.getTime();
            workTimes.put(start, (double) diff / (60 * 60 * 1000));
        }
        return workTimes;
    }

}
