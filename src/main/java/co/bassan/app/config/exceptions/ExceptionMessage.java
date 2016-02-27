package co.bassan.app.config.exceptions;

import lombok.Data;

/**
 * Class to manage on a standardized way the messages sent in
 * the response when a message is needed.
 *
 * @author pbastidas
 */
@Data
public class ExceptionMessage {

    private ExceptionMessages.MessageType type;
    private String message;

    public ExceptionMessage(ExceptionMessages.MessageType type, String message){
        this.type = type;
        this.message = message;
    }
}
