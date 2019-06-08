package Kwetter.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Token implements Serializable {

    private User user;
    private String token;
    private Date date;

    public Token() {
    }

    public Token(User user, String token, Date date) {
        this.user = user;
        this.token = token;
        this.date = date;
    }
}
