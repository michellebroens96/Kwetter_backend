package Kwetter.DTO;

import Kwetter.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    //fields
    private int userId;
    private String name;
    private String username;
    private String location;
    private String web;
    private String bio;
    private String image;

    private List<FollowerDTO> followers = new ArrayList<>();
    private List<FollowerDTO> following = new ArrayList<>();

    //getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public String getWeb() {
        return web;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public List<FollowerDTO> getFollowers() {
        return followers;
    }

    public List<FollowerDTO> getFollowings() {
        return following;
    }

    //setters

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFollowers(List<FollowerDTO> followers) {
        this.followers = followers;
    }

    public void setFollowings(List<FollowerDTO> following) {
        this.following = following;
    }

    //constructors
    public UserDTO(User userEntity) {
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
        this.location = userEntity.getLocation();
        this.web = userEntity.getWeb();
        this.bio = userEntity.getBio();
        this.image = userEntity.getImage();
        for(User user : userEntity.getFollowers()) {
            followers.add(new FollowerDTO(user.getUserId(), user.getName()));
        }
        for(User user : userEntity.getFollowing()) {
            following.add(new FollowerDTO(user.getUserId(), user.getName()));
        }
    }
}
