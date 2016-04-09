package co.bassan.app.contacts.boundary;

import co.bassan.app.config.interceptors.Paginable;
import co.bassan.app.config.interceptors.PaginatorInterceptor;
import co.bassan.app.contacts.dto.FilterParameters;
import co.bassan.app.contacts.model.Contact;
import org.apache.commons.lang.StringUtils;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.CriteriaContainer;
import org.mongodb.morphia.query.CriteriaContainerImpl;
import org.mongodb.morphia.query.Query;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
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
    @Paginable
    public Response getFiltered(
              @BeanParam FilterParameters params
    ){
        final Query<Contact> query = manager.getQueryFiltered(params);

        long totalRows = query.countAll();

        final List<Contact> contacts = query.asList();

        return Response
                .ok(contacts)
                .header(PaginatorInterceptor.TOTAL_ROWS_HEADER, totalRows)
                .build();
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
