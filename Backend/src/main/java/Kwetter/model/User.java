package Kwetter.model;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "followedId")},
               inverseJoinColumns = {@JoinColumn(name = "followerId")})
    @ElementCollection(targetClass = User.class)
    private List<User> followers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "followerId")},
               inverseJoinColumns = {@JoinColumn(name = "followedId")})
    @ElementCollection(targetClass = User.class)
    private List<User> following = new ArrayList<>();

    //methods
    public void AddFollower(User follower) {
        followers.add(follower);
    }

}
