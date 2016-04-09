package co.bassan.app.opportunity.validator;

import co.bassan.app.config.exceptions.BusinessExceptionBuilder;
import co.bassan.app.opportunity.model.Opportunity;

/**
 * Created by pbastidas on 4/4/16.
 */
public interface Validator {

    BusinessExceptionBuilder validate(Opportunity opportunity);

}
