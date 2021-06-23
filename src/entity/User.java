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

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "hashedPassword")
    private String hashedPassword;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    public User() {}
    public User(final String email, final String password, final boolean isAdmin) {
        this.email = email;
        this.hashedPassword = PasswordHashing.Hash(password);
        this.isAdmin = isAdmin;
    }

    public String getEmail() { return email; }
    public void setEmail(final String email) { this.email = email; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(final String password) {
        String hashedPass = PasswordHashing.Hash(password);
        if(hashedPass != null)
            this.hashedPassword = hashedPass;
    }

    public void setAdmin(final boolean isAdmin) { this.isAdmin = isAdmin; }
    public boolean getAdmin() { return isAdmin; }
}
