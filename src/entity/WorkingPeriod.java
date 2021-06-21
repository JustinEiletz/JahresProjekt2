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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "started_working", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedWorking;

    @Column(name = "stopped_working")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stoppedWorking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartedWorking() {
        return startedWorking;
    }

    public Date getStoppedWorking() {
        return stoppedWorking;
    }

    public void setStoppedWorking(Date stoppedWorking) {
        this.stoppedWorking = stoppedWorking;
    }

    public void setStartedWorking(Date startedWorking) {
        this.startedWorking = startedWorking;
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
