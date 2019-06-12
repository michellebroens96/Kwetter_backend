package Kwetter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Kweet {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kweetId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String content;
    private Date date;

    //getters
    public int getKweetId() {
        return kweetId;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    //setters
    public void setKweetId(int kweetId) {
        this.kweetId = kweetId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //constructor
    public Kweet() {

    }
}