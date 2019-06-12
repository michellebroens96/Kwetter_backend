package Kwetter.dto;

import lombok.Data;

@Data
public class FollowerDTO {

    //fields
    private int userId;
    private String name;

    //constructor
    public FollowerDTO(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
