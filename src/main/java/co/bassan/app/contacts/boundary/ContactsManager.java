package co.bassan.app.contacts.boundary;

import co.bassan.app.contacts.model.Contact;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by pbastidas on 1/26/16.
 */
public class ContactsManager extends BasicDAO<Contact, ObjectId> {

    public ContactsManager(Datastore ds) {
        super(ds);
    }

    public boolean updateContact(String id, Contact contact) {
        final Contact contactStoraged = get(new ObjectId(id));

        if (contactStoraged == null)
            return false;

        contactStoraged.setEmails(contact.getEmails());
        contactStoraged.setFirstName(contact.getFirstName());
        contactStoraged.setLastName(contact.getLastName());
        contactStoraged.setPhones(contact.getPhones());

        save(contactStoraged);

        return true;
    }
}
