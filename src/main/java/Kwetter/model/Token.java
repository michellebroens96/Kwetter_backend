package Kwetter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;
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
