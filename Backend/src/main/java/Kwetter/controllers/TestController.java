package Kwetter.controllers;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/resource")
public class TestController
{
    @GET
    @Path("/hi")
    public String hi()
    {
        return "hi!";
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/posttest")
    public String post(String userJson){
        return "new Post";
    }
}
