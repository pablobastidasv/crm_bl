package co.bassan.app.config;

import co.bassan.app.contacts.boundary.ContactsManager;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

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

    @Produces
    public Datastore datastoreProducer(){

        final Morphia morphia = new Morphia();
        final MongoClient mongo = new MongoClient();

        return morphia.createDatastore(mongo, "crm");

    }

}
