package co.bassan.app.contacts.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by pbastidas on 1/26/16.
 */
@Data
@Entity
public class Contact {

    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    @Embedded
    private List<Phone> phones;
    @Embedded
    private List<Email> emails;

}
