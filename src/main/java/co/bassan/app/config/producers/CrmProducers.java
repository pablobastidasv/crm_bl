package co.bassan.app.config.producers;

import co.bassan.app.contacts.boundary.ContactsManager;
import co.bassan.app.opportunity.boundary.OpportunityManager;
import org.mongodb.morphia.Datastore;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by pbastidas on 1/26/16.
 */
public class CrmProducers {

    @Inject
    private Datastore ds;

    @Produces
    public ContactsManager contactsManagerProducer(){
        return new ContactsManager(ds);
    }


}
