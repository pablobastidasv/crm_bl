package co.bassan.app.contacts.boundary;

import co.bassan.app.contacts.model.Contact;
import org.mongodb.morphia.Key;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by pbastidas on 1/25/16.
 */
@Path("contacts")
@Produces("application/json")
public class ContactsResource {

    @Inject
    private ContactsManager manager;

    @GET
    public List<Contact> findAll(){
        return manager.find().asList();
    }

    @Path("{id}")
    public ContactResource get(@PathParam("id") String id){
        return new ContactResource(id, manager);
    }

    @POST
    public Response create(@Context UriInfo uriInfo,Contact contact){
        final Key<Contact> contactKey = manager.save(contact);

        return Response
                .status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + contactKey.getId())
                .build();
    }

}
