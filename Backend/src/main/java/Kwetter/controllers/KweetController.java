package Kwetter.controllers;

import Kwetter.services.KweetService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/kweet")
public class KweetController
{
    @Inject
    private KweetService kweetService;
    private Gson gson = new Gson();

    @PUT
    @Path("{userId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public void createKweet(@PathParam("userId") int userid, String content)
    {
        kweetService.createKweet(userid, content);
    }

    @Path("search")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public String SearchKweet(String searchContent)
    {
        Gson gson = new Gson();
        return gson.toJson(kweetService.searchKweet(searchContent));
    }

    @GET
    @Path("timeline/{userId}")
    @Produces(APPLICATION_JSON)
    public Response GetTimeLine(@PathParam("userId") int userId)
    {
        Gson gson = new Gson();

        String json = gson.toJson(kweetService.getTimeLine(userId));
        return Response.ok(json).build();
    }

    @GET
    @Path("latest/{visitedId}")
    @Produces(APPLICATION_JSON)
    public Response getLatestKweets(@PathParam("visitedId") int visitedId)
    {
        gson.toJson(kweetService.getRecentKweets(visitedId));
        return Response.ok(gson).build();
    }
}
