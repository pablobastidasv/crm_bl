package co.bassan.app.opportunity.validator.implementations;

import co.bassan.app.config.exceptions.BusinessExceptionBuilder;
import co.bassan.app.config.exceptions.ExceptionMessages;
import co.bassan.app.opportunity.model.Opportunity;
import co.bassan.app.opportunity.validator.Validator;

import javax.enterprise.inject.Default;

/**
 * Created by pbastidas on 4/4/16.
 */
@Default
public class ValCreation implements Validator {
    @Override
    public BusinessExceptionBuilder validate(Opportunity opportunity) {

        final BusinessExceptionBuilder builder = BusinessExceptionBuilder.builder();

        if (opportunity.getComment() == null || opportunity.getComment().isEmpty()) {
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "The opportunity must have a comment.");
        }

        if (opportunity.getContacts() == null || opportunity.getContacts().isEmpty()) {
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "The opportunity must have at least one contact.");
        }

        return builder;
    }
}
