package Kwetter.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private String location;
    private String web;
    private String bio;
    private String image;
    private String token;
    private Role role = Role.USER;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> following = new ArrayList<>();

    //methods
    public void addFollower(User follower) {
        following.add(follower);
    }
}
