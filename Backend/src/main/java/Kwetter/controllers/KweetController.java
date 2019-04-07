package Kwetter.controllers;

import Kwetter.services.KweetService;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/kweet")
public class KweetController
{
    @Inject
    KweetService kweetService;

    @PUT
    @Path("{userId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public void createKweet(@PathParam("userId") int userid, String content)
    {
        kweetService.createKweet(userid, content);
    }
}
