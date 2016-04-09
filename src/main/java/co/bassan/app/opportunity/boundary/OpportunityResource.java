package co.bassan.app.opportunity.boundary;

import co.bassan.app.opportunity.model.Opportunity;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by pbastidas on 2/19/16.
 */
@Produces("application/json")
public class OpportunityResource {

    private String id;
    private OpportunityManager manager;

    public OpportunityResource(String id, OpportunityManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @GET
    public Response get(){
        final Opportunity opportunity = manager.get(new ObjectId(id));

        if(opportunity == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(opportunity)
                    .build();
        }
    }


    @PUT
    public Response update(Opportunity opportunity) {
        opportunity.setId(new ObjectId(id));

        if(manager.updateOpportunity(opportunity)) {
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
