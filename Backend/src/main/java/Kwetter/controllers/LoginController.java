package Kwetter.controllers;

import Kwetter.DTO.UserDTO;
import Kwetter.models.User;
import Kwetter.services.LoginService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/login")
public class LoginController
{
    @Inject
    private LoginService loginService;
    private Gson gson = new Gson();

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/register")
    public Response register(String userJson)
    {
        String json = "";
        User createdUser = null;
        User user = gson.fromJson(userJson, User.class);
        if (loginService.checkUsername(user.getUsername()) == null)
        {
            createdUser = loginService.register(user.getUsername(), user.getPassword());
            json = gson.toJson(createdUser);
        }
        return Response.ok(json).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response Login(String userJson)
    {
        User attemptUser = gson.fromJson(userJson, User.class);
        UserDTO user;
        String json = "";
        user = new UserDTO(loginService.login(attemptUser.getUsername(), attemptUser.getPassword()));
        if (user != null)
        {
            json = gson.toJson(user);
        }
        return Response.ok(json).build();
    }
}

