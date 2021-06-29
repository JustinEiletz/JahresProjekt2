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

    @FXML
    private void linkUserAdministration() { ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getUserAdministrationScene()); }

    private void setupEmployeeTableView() {
        List<User> users = userDao.findAll();
        EmployeeTableView eTV = new EmployeeTableView();
        TableColumn<EmployeeTableView, String> id = new TableColumn<>(eTV.getUserId());
        TableColumn<EmployeeTableView, String> loginName = new TableColumn<>(eTV.getUserLoginName());
        TableColumn<EmployeeTableView, String> workingHours = new TableColumn<>(eTV.getUserWorkingHours());
        userTV.getColumns().addAll(id, loginName, workingHours);

        id.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserId()));
        id.setMinWidth(90);

        loginName.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserId()));
        loginName.setMinWidth(160);

        workingHours.setCellValueFactory(new PropertyValueFactory<>(eTV.getUserLoginName()));
        workingHours.setMinWidth(240);

        for (User user : users) {
            List<WorkingPeriod> workingPeriodsOfUser = workingPeriodDao.findByUserId(user.getId());
            if (workingPeriodsOfUser.size() > 0) {
                eTV.setId(workingPeriodsOfUser.get(0).getUser().getId());
                eTV.setLoginName((workingPeriodsOfUser.get(0).getUser().getLoginName()));
                eTV.setWorkingHours(7D);
                userTV.getItems().add(eTV);
                workingPeriodsOfUser.clear();
            }
        }
    }
}
