package zone.dragon.basepom.dwbundle4x;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorldResource {

    @GET
    public String sayHello() {
        return "Hello, World!";
    }
}
