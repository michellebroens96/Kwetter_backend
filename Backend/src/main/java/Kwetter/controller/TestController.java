package Kwetter.controller;

import Kwetter.dto.UserDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/test")
public class TestController {

    @GET
    @Consumes(APPLICATION_JSON)
    public Response getUsers(){
        UserDTO userDTO = new UserDTO();
        userDTO.setToken("askdfhaksdhffags");
        userDTO.setBio("mijn bio");
        userDTO.setImage("mijn image");
        userDTO.setName("mijn naam");
        userDTO.setUserId(1);
        userDTO.setLocation("locatie is Eindhoven");
        userDTO.setUsername("username");
        userDTO.setWeb("web");
        return Response.ok().entity(userDTO).build();
    }
}
