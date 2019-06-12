package Kwetter.resource;

import Kwetter.service.KweetService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/kweet")
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @PUT
    @Path("{userId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public void createKweet(@PathParam("userId") int userId, String content) {
        kweetService.createKweet(userId, content);
    }

    @Path("search")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public String SearchKweet(String searchContent) {
        Gson gson = new Gson();
        return gson.toJson(kweetService.searchKweet(searchContent));
    }

    @GET
    @Path("timeline/{userId}")
    @Produces(APPLICATION_JSON)
    public Response GetTimeLine(@PathParam("userId") int userId) {
        Gson gson = new Gson();

        String json = gson.toJson(kweetService.getTimeLine(userId));
        return Response.ok(json).build();
    }

    @GET
    @Path("/latestKweets/{userId}")
    @Produces(APPLICATION_JSON)
    public Response getLatestKweets(@PathParam("userId") int userId) {
        Gson gson = new Gson();

        String json = gson.toJson(kweetService.getLatestKweets(userId));
        return Response.ok(json).build();
    }
}
