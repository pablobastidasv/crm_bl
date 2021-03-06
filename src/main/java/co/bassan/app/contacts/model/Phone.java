package co.bassan.app.contacts.model;

import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by pbastidas on 1/26/16.
 */
@Data
@Embedded
public class Phone {

    private PhoneKind kind;
    private String number;
    private String extension;

}
