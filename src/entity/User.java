package entity;

import calculations.PasswordHashing;
import enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@NamedQueries({
        @NamedQuery(
                name = "User.findAll",
                query = "SELECT U FROM User U"
        ),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "hashedPassword")
    private String hashedPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {}
    public User(final String email, final String password, final UserRole role) {
        this.email = email;
        this.hashedPassword = PasswordHashing.Hash(password);
        this.role = role;
    }

    public Integer getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(final String email) { this.email = email; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(final String password) {
        this.hashedPassword = PasswordHashing.Hash(password);
    }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    @Override
    public String toString() {
        return role.toString() + ": "  + email;
    }

}
