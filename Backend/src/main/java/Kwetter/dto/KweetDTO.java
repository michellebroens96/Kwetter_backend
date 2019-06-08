package Kwetter.dto;

import Kwetter.model.Kweet;

import java.util.Date;

public class KweetDTO {

    //fields
    private int kweetId;
    private int userId;
    private String creatorName;
    private String content;
    private Date date;

    //getters
    public int getKweetId() {
        return kweetId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCreatorName() {
        return creatorName;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //constructors
    public KweetDTO(Kweet kweet) {
        this.kweetId = kweet.getKweetId();
        this.userId = kweet.getUser().getUserId();
        this.creatorName = kweet.getUser().getName();
        this.content = kweet.getContent();
        this.date = kweet.getDate();
    }
}
