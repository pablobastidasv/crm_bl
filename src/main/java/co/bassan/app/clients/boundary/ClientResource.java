package co.bassan.app.clients.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by pbastidas on 1/25/16.
 */
@Path("clients")
public class ClientResource {

    @GET
    public Response findAll(){
        return Response.ok("Hello world!!!").build();
    }

}
