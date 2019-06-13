package Kwetter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
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
}
