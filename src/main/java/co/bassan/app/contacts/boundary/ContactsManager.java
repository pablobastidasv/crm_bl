package co.bassan.app.contacts.boundary;

import co.bassan.app.contacts.model.Contact;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import javax.enterprise.inject.Produces;

/**
 * Created by pbastidas on 1/26/16.
 */
public class ContactsManager extends BasicDAO<Contact, ObjectId>{

    public ContactsManager(Datastore ds) {
        super(ds);
    }
}
