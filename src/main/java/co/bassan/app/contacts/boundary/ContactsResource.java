package co.bassan.app.contacts.boundary;

import co.bassan.app.contacts.model.Contact;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by pbastidas on 1/25/16.
 */
@Path("contact")
public class ContactsResource {

    @Inject
    private ContactsManager manager;

    @GET
    public Response findAll(){
        return Response.ok("Hello world!!!").build();
    }

    @Path("{id}")
    public ContactResource get(@PathParam("id") String id){
        return new ContactResource(id);
    }

    @POST
    public Response create(Contact contact){
        return Response.ok().build();
    }

}
