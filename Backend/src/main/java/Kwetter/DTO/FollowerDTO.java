package Kwetter.DTO;

public class FollowerDTO {

    //fields
    private int userId;
    private String name;

    //getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    //setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    //constructor
    public FollowerDTO(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
