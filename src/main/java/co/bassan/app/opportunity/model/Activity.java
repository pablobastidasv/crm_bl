package co.bassan.app.opportunity.model;

import co.bassan.app.config.serializers.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.PrePersist;

import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by pbastidas on 2/13/16.
 */
@Embedded
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {

    public enum ActivityType{
        CALL, EMAIL, MEET
    }

    public enum ActivityState {
        DONE, PENDING, CANCEL
    }

    private ActivityType type;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dueDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date creationDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date doneDate;
    private String subject;
    private String creationComments;
    private String executionComments;
    private ActivityState state;
    private String cancelComments;

    @PrePersist
    public void prePersist(){
        if(creationDate == null){
            creationDate  = new Date();
        }

        updateDoneDate();
    }

    @PreUpdate
    public void preUpdate(){
        updateDoneDate();
    }

    private void updateDoneDate() {
        if(state == ActivityState.DONE && doneDate == null){
            doneDate  = new Date();
        }
    }

}
