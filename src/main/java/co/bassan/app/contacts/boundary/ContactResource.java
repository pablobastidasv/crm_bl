package co.bassan.app.contacts.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by pbastidas on 1/25/16.
 */
public class ContactResource {

    private String id;

    public ContactResource(String id) {
        this.id = id;
    }

    @GET
    public Response findById(){
        return Response.ok("Hello world!!!").build();
    }

}
