package co.bassan.app.config.producers;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by pbastidas on 1/29/16.
 */
public class GeneralProduces {

    @Inject
    @Property
    private MongoClient mongo;

    @Produces
    private Datastore datastoreProducer(){


        final Morphia morphia = new Morphia();
        morphia.mapPackage("co.bassan.app.contacts.model");


        final Datastore ds = morphia.createDatastore(mongo, "crm");
        ds.ensureIndexes();

        return ds;
    }
}
