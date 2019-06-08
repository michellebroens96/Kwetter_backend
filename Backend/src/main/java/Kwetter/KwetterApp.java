package Kwetter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class KwetterApp extends Application {

    // Create JAX-RS application.
    public KwetterApp() {
    }
}
