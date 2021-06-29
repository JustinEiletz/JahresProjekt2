package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTE")
@NamedQueries({
        @NamedQuery(
                name = "Note.findAll",
                query = "SELECT N FROM Note N"
        ),
})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne @JoinColumn(name = "userId")
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob()
    @Column(name = "content", columnDefinition = "MEDIUMBLOB", nullable = false)
    private String content;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Note() {}
    public Note(final Integer id, final String title, final String content, final User user)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
