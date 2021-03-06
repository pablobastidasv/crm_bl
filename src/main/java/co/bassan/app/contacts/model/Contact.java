package co.bassan.app.contacts.model;

import co.bassan.app.config.serializers.CustomDateSerializer;
import co.bassan.app.config.serializers.CustomObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

import java.util.Date;
import java.util.List;

/**
 * Created by pbastidas on 1/26/16.
 */
@Data
@Entity(noClassnameStored = true, value = "contact")
public class Contact {

    public static final String FIRST_NAME_FIELD = "firstName";
    public static final String LAST_NAME_FIELD = "lastName";

    @Id
    @JsonSerialize(using = CustomObjectIdSerializer.class)
    private ObjectId id;
    private String firstName;
    private String lastName;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date creationDate;
    @Embedded
    private List<Phone> phones;
    @Embedded
    private List<Email> emails;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

}
