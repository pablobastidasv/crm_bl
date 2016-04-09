package co.bassan.app.opportunity.model;

import co.bassan.app.config.serializers.CustomDateSerializer;
import co.bassan.app.config.serializers.CustomObjectIdSerializer;
import co.bassan.app.contacts.model.Contact;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pbastidas on 2/13/16.
 */
@Entity
@Data
public class Opportunity {

    enum OpportunityState{
        NEW, IN_PROGRESS, LOST, CLOSED
    }

    @Id
    @JsonSerialize(using = CustomObjectIdSerializer.class)
    private ObjectId id;
    private String comment;
    @Reference
    private List<Contact> contacts;
    @Embedded
    private  List<Activity> activities;
    @NotNull
    private OpportunityState state;
}
