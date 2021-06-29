package entity;

import javafx.beans.property.SimpleStringProperty;

public class TenantTableView {

    public final SimpleStringProperty tenantId = new SimpleStringProperty("Id");
    public final SimpleStringProperty tenantForename = new SimpleStringProperty("Forename");
    public final SimpleStringProperty tenantSurName = new SimpleStringProperty("Surname");
    public final SimpleStringProperty tenantPhoneNumber = new SimpleStringProperty("Phone number");

    private Integer id;
    private String foreName;
    private String surName;
    private String phoneNumber;

    public TenantTableView() { }

    public TenantTableView(final Integer id, final String foreName, final String surName, final String phoneNumber) {
        this.id = id;
        this.foreName = foreName;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
    }

    public SimpleStringProperty tenantIdProperty() { return tenantId; }

    public SimpleStringProperty tenantForenameProperty() { return tenantForename; }

    public SimpleStringProperty tenantSurNameProperty() { return tenantSurName; }

    public SimpleStringProperty tenantPhoneNumberProperty() { return tenantPhoneNumber; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getForeName() { return foreName; }
    public void setForeName(String foreName) { this.foreName = foreName; }

    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName;  }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
