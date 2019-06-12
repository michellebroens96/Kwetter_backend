package Kwetter.dto;

import Kwetter.model.Kweet;
import lombok.Data;

import java.util.Date;

@Data
public class KweetDTO {

    //fields
    private int kweetId;
    private int userId;
    private String creatorName;
    private String content;
    private Date date;

    //constructors
    public KweetDTO(Kweet kweet) {
        this.kweetId = kweet.getKweetId();
        this.userId = kweet.getUser().getUserId();
        this.creatorName = kweet.getUser().getName();
        this.content = kweet.getContent();
        this.date = kweet.getDate();
    }
}
