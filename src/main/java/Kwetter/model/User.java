package Kwetter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @JoinTable(name = "userCollections", joinColumns = {
    @JoinColumn(name = "followers", referencedColumnName = "userId", nullable = false)}, inverseJoinColumns = {
    @JoinColumn(name = "following", referencedColumnName = "userId", nullable = false)})
    @ManyToMany
    private List<User> following = new ArrayList<>();
    @ManyToMany(mappedBy = "following")
    private List<User> followers;

    //methods
    public void addFollower(User follower) {
        followers.add(follower);
    }
}
