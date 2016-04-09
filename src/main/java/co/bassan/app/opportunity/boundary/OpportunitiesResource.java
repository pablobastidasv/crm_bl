package co.bassan.app.opportunity.boundary;

import co.bassan.app.config.interceptors.Paginable;
import co.bassan.app.config.interceptors.PaginatorInterceptor;
import co.bassan.app.opportunity.model.Opportunity;
import org.mongodb.morphia.Key;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by pbastidas on 2/10/16.
 */
@Path("opportunities")
@Produces("application/json")
public class OpportunitiesResource {

    @Inject
    private OpportunityManager manager;

    @GET
    @Paginable
    public Response listOpportunities(@QueryParam("qtyPerPage") Integer qtyPerPage, @QueryParam("page") Integer page) {
        long totalRows = manager.createQuery().countAll();

        final List<Opportunity> opportunities;

        if (qtyPerPage != null && page != null) {
            opportunities = manager.createQuery().limit(qtyPerPage).offset(qtyPerPage * page).asList();
        } else {
            opportunities = manager.createQuery().asList();
        }

        return Response.ok(opportunities)
                .header(PaginatorInterceptor.TOTAL_ROWS_HEADER, totalRows)
                .build();
    }

    @Path("{id}")
    public OpportunityResource getOpportunity(@PathParam("id") String id) {
        return new OpportunityResource(id, manager);
    }

    @POST
    public Response create(@Context UriInfo uriInfo, Opportunity opportunity) {
        final Key<Opportunity> key = manager.save(opportunity);

        return Response
                .status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + key.getId())
                .build();
    }

}
