package entity;

import calculations.PasswordHashing;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    public User() {}
    public User(final Integer id,
                final String email,
                final String password,
                final Boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.hashedPassword = PasswordHashing.Hash(password);
        this.isAdmin = isAdmin;
    }



    public String getHashed_password() {
        return hashedPassword;
    }

    public void setPassword(String password) {
        String hashedPass = PasswordHashing.Hash(password);
        if(hashedPass != null) {
            this.hashedPassword = hashedPass;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
