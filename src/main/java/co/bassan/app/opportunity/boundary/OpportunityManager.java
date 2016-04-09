package co.bassan.app.opportunity.boundary;

import co.bassan.app.config.exceptions.BusinessExceptionBuilder;
import co.bassan.app.config.exceptions.ExceptionMessages;
import co.bassan.app.opportunity.model.Opportunity;
import co.bassan.app.opportunity.validator.Edition;
import co.bassan.app.opportunity.validator.Validator;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Inject;

/**
 * Created by pbastidas on 2/13/16.
 */
public class OpportunityManager extends BasicDAO<Opportunity, ObjectId> {

    @Inject
    public OpportunityManager(Datastore ds) {
        super(ds);
    }

    @Inject
    private Validator creationValidator;

    @Inject
    @Edition
    private Validator editionValidator;

    @Override
    public Key<Opportunity> save(Opportunity opportunity) {
        final BusinessExceptionBuilder validations = creationValidator.validate(opportunity);
        if(validations.hasMessages()) throw validations.build();

        return super.save(opportunity);
    }

    public boolean updateOpportunity(Opportunity opportunity) {
        validateEdition(opportunity);

        save(opportunity);

        return true;
    }

    private void validateEdition(Opportunity opportunity) {
        final BusinessExceptionBuilder validations = editionValidator.validate(opportunity);
        if(validations.hasMessages()) throw validations.build();

        // Validate if the id exists
        final BusinessExceptionBuilder builder = BusinessExceptionBuilder.builder();

        if (get(opportunity.getId()) == null) {
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "This opportunity doesn't exist");
        }

        if (builder.hasMessages()) {
            throw builder.build();
        }
    }
}
