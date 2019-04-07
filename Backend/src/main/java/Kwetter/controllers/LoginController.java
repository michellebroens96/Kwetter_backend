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

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/register")
    public Response register(String userJson)
    {
        Gson g = new Gson();
        String json = "";
        User createdUser = null;
        User user = g.fromJson(userJson, User.class);
        if (loginService.checkUsername(user.getUsername()) == null)
        {
            createdUser = loginService.register(user.getUsername(), user.getPassword());
            json = g.toJson(createdUser);
        }
        return Response.ok(json).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response Login(String userJson)
    {
        Gson g = new Gson();
        User attemptUser = g.fromJson(userJson, User.class);
        UserDTO user;
        String json = "";
        user = new UserDTO(loginService.login(attemptUser.getUsername(), attemptUser.getPassword()));
        if (user != null)
        {
            json = g.toJson(user);
        }
        return Response.ok(json).build();
    }
}

