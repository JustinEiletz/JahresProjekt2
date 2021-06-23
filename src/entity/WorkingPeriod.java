package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WORKING_PERIOD")
@NamedQueries({
        @NamedQuery(
                name = "WorkingPeriod.findAll",
                query = "SELECT P FROM WorkingPeriod P"
        ),
})
public class WorkingPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "startedWorking", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedWorking;

    @Column(name = "stoppedWorking")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stoppedWorking;

    public WorkingPeriod() {}
    public WorkingPeriod(final User user, final Date startedWorking, final Date stoppedWorking) {
        this.user = user;
        this.startedWorking = startedWorking;
        this.stoppedWorking = stoppedWorking;
    }

    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(final User user) {
        this.user = user;
    }

    public Date getStartedWorking() {
        return startedWorking;
    }
    public void setStartedWorking(final Date startedWorking) {
        this.startedWorking = startedWorking;
    }

    public Date getStoppedWorking() {
        return stoppedWorking;
    }
    public void setStoppedWorking(final Date stoppedWorking) {
        this.stoppedWorking = stoppedWorking;
    }

    @Override
    public String toString() {
        return "WorkingPeriodEntry{" +
                "id=" + id +
                ", user=" + user +
                ", startedWorking=" + startedWorking +
                ", stoppedWorking=" + stoppedWorking +
                '}';
    }
}
