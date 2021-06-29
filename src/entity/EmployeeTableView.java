package entity;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeTableView extends User {

    public final SimpleStringProperty userId = new SimpleStringProperty("Id");
    public final SimpleStringProperty userLoginName = new SimpleStringProperty("LoginName");
    public final SimpleStringProperty userWorkingHours = new SimpleStringProperty("WorkingPeriod");

    private Integer id;
    private String loginName;
    private Double workingHours;

    public EmployeeTableView() { }

    public EmployeeTableView(final Integer userId, final String userLoginName, final Double userWorkingHours) {
        this.id = userId;
        this.loginName = userLoginName;
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

    public String getLoginName() { return loginName; }
    public void setLoginName(final String loginName) { this.loginName = loginName; }

    public Double getWorkingHours() { return workingHours; }
    public void setWorkingHours(final Double workingHours) { this.workingHours = workingHours; }
}
