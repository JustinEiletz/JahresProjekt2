package objects;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "loginName")
    private String loginName;

    @Column(name = "timeStamp")
    private Date timeStamp;

    @Column(name = "textMessage")
    private String textMessage;

    public Message(Integer id, String loginName, Date timeStamp, String textMessage) {
        this.id = id;
        this.loginName = loginName;
        this.timeStamp = timeStamp;
        this.textMessage = textMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
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
