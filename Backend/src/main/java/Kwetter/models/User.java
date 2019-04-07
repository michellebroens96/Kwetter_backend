package Kwetter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User
{
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "followedId")}, inverseJoinColumns = {@JoinColumn(name = "followerId")})
    @ElementCollection(targetClass = User.class)
    private List<User> followers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "followerId")}, inverseJoinColumns = {@JoinColumn(name = "followedId")})
    @ElementCollection(targetClass = User.class)
    private List<User> following = new ArrayList<>();

    //getters
    public int getUserId()
    {
        return userId;
    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLocation()
    {
        return location;
    }

    public String getWeb()
    {
        return web;
    }

    public String getBio()
    {
        return bio;
    }

    public String getImage()
    {
        return image;
    }

    public List<User> getFollowers()
    {
        return followers;
    }

    public List<User> getFollowing()
    {
        return following;
    }

    //setters
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setWeb(String web)
    {
        this.web = web;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setFollowers(List<User> followers)
    {
        this.followers = followers;
    }

    public void setFollowing(List<User> following)
    {
        this.following = following;
    }

    //constructor


    public User()
    {
    }

    //methods
    public void AddFollower(User follower)
    {
        followers.add(follower);
    }

}
