package entity;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeTableView extends User {

    public final SimpleStringProperty userId = new SimpleStringProperty("Id");
    public final SimpleStringProperty userLoginName = new SimpleStringProperty("Login");
    public final SimpleStringProperty userWorkingHours = new SimpleStringProperty("WorkingHours");

    private Integer id;
    private String login;
    private Double workingHours;

    public EmployeeTableView() { }

    public EmployeeTableView(final Integer userId, final String userLoginName, final Double userWorkingHours) {
        this.id = userId;
        this.login = userLoginName;
        this.workingHours = userWorkingHours;
    }

    public String getUserId() { return userId.get(); }
    public SimpleStringProperty userIdProperty() { return userId; }

    public String getUserLoginName() { return userLoginName.get(); }
    public SimpleStringProperty userLoginNameProperty() { return userLoginName; }

    public String getUserWorkingHours() { return userWorkingHours.get(); }
    public SimpleStringProperty userWorkingHoursProperty() { return userWorkingHours; }

    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(final String login) { this.login = login; }

    public Double getWorkingHours() { return workingHours; }
    public void setWorkingHours(final Double workingHours) { this.workingHours = workingHours; }
}
