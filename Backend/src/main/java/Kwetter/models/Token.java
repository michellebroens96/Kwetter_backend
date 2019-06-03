package Kwetter.models;

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
}
