package co.bassan.app.opportunity.validator.implementations;

import co.bassan.app.config.exceptions.BusinessExceptionBuilder;
import co.bassan.app.config.exceptions.ExceptionMessages;
import co.bassan.app.opportunity.model.Activity;
import co.bassan.app.opportunity.model.Opportunity;
import co.bassan.app.opportunity.validator.Edition;
import co.bassan.app.opportunity.validator.Validator;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by pbastidas on 4/4/16.
 */
@Edition
public class ValEdition implements Validator {

    @Inject
    private Validator creationValidations;

    @Override
    public BusinessExceptionBuilder validate(Opportunity opportunity) {
        // Use the same validation that create
        final BusinessExceptionBuilder builder = creationValidations.validate(opportunity);;

        if (Objects.isNull(opportunity.getId()) || opportunity.getId().toString().isEmpty()) {
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "The id is needed to edit the opportunity");
        }

        if(opportunity.getActivities().isEmpty()){
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "The opportunity must have at least one activity.");
        }

        validateActivities(builder, opportunity.getActivities());

        return builder;
    }

    private void validateActivities(BusinessExceptionBuilder builder, List<Activity> activities) {
        for (int i = 1; i <= activities.size(); i++) {
            final Activity activity = activities.get( i - 1 );

            validateActivity(i, activity, builder);
        }
    }
    private void validateActivity(Integer i, Activity activity, BusinessExceptionBuilder builder) {
        if(Objects.isNull(activity.getType())){
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "Activity %d must have defined the type.", i);
        }

        if(StringUtils.isEmpty(activity.getSubject())){
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "Activity %d must have the subject.", i);
        }

        if(Objects.isNull(activity.getState())){
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "Activity %d must have the state.", i);
        } else{
            commentValidation(i, activity, builder);
        }

        if(Objects.isNull(activity.getDueDate())){
            builder.addMessage(ExceptionMessages.MessageType.ERROR, "Activity %d must defined the due date.", i);
        }

    }

    private void commentValidation(Integer i, Activity activity, BusinessExceptionBuilder builder) {
        switch (activity.getState()){
            case DONE:
                if(Objects.isNull(activity.getExecutionComments())){
                    builder.addMessage(ExceptionMessages.MessageType.ERROR, "Activity %d is Done, you have to specify a execution comment.", i);
                }
                break;
            case CANCEL:
                if(Objects.isNull(activity.getCancelComments())){
                    builder.addMessage(ExceptionMessages.MessageType.ERROR, "Activity %d is Canceled, you have to specify a cancellation comment.", i);
                }
                break;
        }
    }
}
