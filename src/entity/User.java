package entity;

import calculations.PasswordHashing;
import enums.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USER")
@Table(name = "USER")
@NamedQueries({
        @NamedQuery(
                name = "User.findAll",
                query = "SELECT U FROM USER U"
        ),
        @NamedQuery(
                name = "User.findByLogin",
                query = "SELECT U FROM USER U WHERE U.loginName = :login OR U.email = :login"
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "loginName", unique = true)
    private String loginName;

    @Column(name = "hashedPassword")
    private String hashedPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<WorkingPeriod> workingPeriod;

    public User() {}
    public User(final String email, final String loginName, final String password, final UserRole role) {
        this.email = email;
        this.loginName = loginName;
        this.hashedPassword = PasswordHashing.Hash(password);
        this.role = role;
    }

    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(final String email) { this.email = email; }

    public String getLoginName() { return loginName; }
    public void setLoginName(final String loginName) { this.loginName = loginName; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(final String password) { this.hashedPassword = PasswordHashing.Hash(password); }

    public UserRole getRole() { return role; }
    public void setRole(final UserRole role) { this.role = role; }

    public List<WorkingPeriod> getWorkingPeriod() {
        if (workingPeriod == null) {
            return  new ArrayList<>();
        }
        return workingPeriod;
    }
    public void setWorkingPeriod(final List<WorkingPeriod> workingPeriod) { this.workingPeriod = workingPeriod; }

    @Override
    public String toString() { return role.toString() + ": "  + loginName + " " + email; }

}
