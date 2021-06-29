package entity;

import javafx.beans.property.SimpleStringProperty;

public class TenantTableView extends Tenant{

    public final SimpleStringProperty tenantId = new SimpleStringProperty("Id");
    public final SimpleStringProperty tenantForename = new SimpleStringProperty("ForeName");
    public final SimpleStringProperty tenantSurName = new SimpleStringProperty("SurName");
    public final SimpleStringProperty tenantPhoneNumber = new SimpleStringProperty("PhoneNumber");

    private Integer id;
    private String foreName;
    private String surName;
    private String phoneNumber;

    public TenantTableView() { this(null, "", "", ""); }

    public TenantTableView(final Integer tenantId, final String tenantForeName, final String tenantSurName, final String tenantPhoneNumber) {
        this.id = tenantId;
        this.foreName = tenantForeName;
        this.surName = tenantSurName;
        this.phoneNumber = tenantPhoneNumber;
    }

    public SimpleStringProperty tenantIdProperty() { return tenantId; }
    public String getTenantId() { return tenantId.get(); }

    public SimpleStringProperty tenantForenameProperty() { return tenantForename; }
    public String getTenantForename() { return tenantForename.get(); }

    public SimpleStringProperty tenantSurNameProperty() { return tenantSurName; }
    public String getTenantSurName() { return tenantSurName.get(); }

    public SimpleStringProperty tenantPhoneNumberProperty() { return tenantPhoneNumber; }
    public String getTenantPhoneNumber() { return tenantPhoneNumber.get(); }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getForeName() { return foreName; }
    public void setForeName(String foreName) { this.foreName = foreName; }

    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName;  }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
