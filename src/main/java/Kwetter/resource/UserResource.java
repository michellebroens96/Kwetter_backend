package Kwetter.resource;

import Kwetter.dto.UserDTO;
import Kwetter.service.UserService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/profilepage/{visitedId}")
public class UserResource {

    @Inject
    private UserService userService;
    private Gson gson = new Gson();

    @Path("{visitorId}")
    @POST
    @Produces(TEXT_PLAIN)
    public Response followUser(@PathParam("visitorId") int followedId, @PathParam("visitedId") int followerId) {
        userService.follow(followedId, followerId);
        return Response.ok().build();
    }

    @GET
    @Produces(TEXT_PLAIN)
    public Response GetUser(@PathParam("visitedId") int visitedId) {
        String userJson = gson.toJson(userService.getUserById(visitedId));
        return Response.ok(userJson).build();
    }

    @Path("edit/{visitorId}")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response EditProfile(@PathParam("visitorId") int visitorId, @PathParam("visitedId") int visitedId,
                                String profileInfoJson) {
        String json = "";
        if(visitedId == visitorId) {
            UserDTO user = gson.fromJson(profileInfoJson, UserDTO.class);
            json = gson.toJson(userService.editUser(visitorId, user));
        }
        return Response.ok(json).build();
    }
}
