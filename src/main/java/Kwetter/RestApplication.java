package Kwetter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
//The java class declares root resource and provider classes
public class RestApplication extends Application {
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
}
