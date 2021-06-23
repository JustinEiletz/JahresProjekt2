package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CHAT")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "loginName")
    private String loginName;

    @Column(name = "timeStamp")
    private Date timeStamp;

    @Column(name = "textMessage")
    private String textMessage;

    public Message() {}
    public Message(final String loginName, final Date timeStamp, final String textMessage) {
        this.loginName = loginName;
        this.timeStamp = timeStamp;
        this.textMessage = textMessage;
    }

    public Integer getId() { return id; }

    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(final Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTextMessage() {
        return textMessage;
    }
    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", timeStamp=" + timeStamp +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }
}
