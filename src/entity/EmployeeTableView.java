package entity;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class EmployeeTableView extends User {

    public final SimpleStringProperty userId = new SimpleStringProperty("Id");
    public final SimpleStringProperty userLoginName = new SimpleStringProperty("LoginName");
    public final SimpleStringProperty userWorkingHours = new SimpleStringProperty("WorkingHours");
    public final SimpleStringProperty userWorkingDay = new SimpleStringProperty("WorkingDay");

    private Integer id;
    private String login;
    private Double workingHours;
    private Date workingDay;

    public EmployeeTableView() { }

    public EmployeeTableView(final Integer userId, final String userLoginName, final Double userWorkingHours, final Date workingDay) {
        this.id = userId;
        this.login = userLoginName;
        this.workingHours = userWorkingHours;
        this.workingDay = workingDay;
    }

    public String getUserId() { return userId.get(); }
    public SimpleStringProperty userIdProperty() { return userId; }

    public String getUserLoginName() { return userLoginName.get(); }
    public SimpleStringProperty userLoginNameProperty() { return userLoginName; }

    public String getUserWorkingHours() { return userWorkingHours.get(); }
    public SimpleStringProperty userWorkingHoursProperty() { return userWorkingHours; }

    public String getUserWorkingDay() { return userWorkingDay.get(); }
    public SimpleStringProperty userWorkingDayProperty() { return userWorkingDay; }

    public Date getWorkingDay() { return workingDay; }
    public void setWorkingDay(Date workingDay) { this.workingDay = workingDay; }

    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(final String login) { this.login = login; }

    public Double getWorkingHours() { return workingHours; }
    public void setWorkingHours(final Double workingHours) { this.workingHours = workingHours; }
}
