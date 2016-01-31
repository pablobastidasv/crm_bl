package co.bassan.app.contacts.boundary;

import co.bassan.app.contacts.model.Contact;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by pbastidas on 1/25/16.
 */
@Produces("application/json")
public class ContactResource {

    private String id;
    private ContactsManager manager;

    public ContactResource(String id, ContactsManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @GET
    public Contact findById() {
        return manager.get(new ObjectId(id));
    }

    @PUT
    public Response update(Contact contact) {
        if(manager.updateContact(id, contact)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public Response delete() {

        final WriteResult result = manager.deleteById(new ObjectId(id));

        if (result.getN() > 0)
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
