package Kwetter.dto;

import Kwetter.model.User;
import lombok.Data;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO implements Principal {

    //fields
    private int userId;
    private String name;
    private String username;
    private String location;
    private String web;
    private String bio;
    private String image;
    private String token;

    //constructors
    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.location = user.getLocation();
        this.web = user.getWeb();
        this.bio = user.getBio();
        this.image = user.getImage();
        this.token = user.getToken();
    }
}
