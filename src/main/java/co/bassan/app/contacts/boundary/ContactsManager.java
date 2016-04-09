package co.bassan.app.contacts.boundary;

import co.bassan.app.contacts.dto.FilterParameters;
import co.bassan.app.contacts.model.Contact;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

import static co.bassan.app.contacts.model.Contact.FIRST_NAME_FIELD;
import static co.bassan.app.contacts.model.Contact.LAST_NAME_FIELD;

/**
 * Created by pbastidas on 1/26/16.
 */
public class ContactsManager extends BasicDAO<Contact, ObjectId> {

    public ContactsManager(Datastore ds) {
        super(ds);
    }

    public boolean updateContact(String id, Contact contact) {
        final Contact contactStored = get(new ObjectId(id));

        if (contactStored == null)
            return false;

        contact.setId(new ObjectId(id));

        save(contact);

        return true;
    }

    public Query<Contact> getQueryFiltered(FilterParameters params){
        Query<Contact> query = createQuery();

        List<Criteria> criteriaList = new ArrayList<>();

        if(StringUtils.isNotEmpty(params.getFirstName())){
            criteriaList.add(query.criteria(FIRST_NAME_FIELD).containsIgnoreCase(params.getFirstName()));
        }
        if(StringUtils.isNotEmpty(params.getLastName())){
            criteriaList.add(query.criteria(LAST_NAME_FIELD).containsIgnoreCase(params.getLastName()));
        }

        criteriaList.forEach(query::or);

        if(params.getQtyPerPage() != null && params.getPage() != null){
            query.limit(params.getQtyPerPage()).offset(params.getQtyPerPage() * params.getPage());
        }

        return query;
    }
}
