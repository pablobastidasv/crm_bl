package co.bassan.app.config.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object class to encapsulate all possible messages needed in the exception business
 *
 * @author pbastidas
 */
public class ExceptionMessages {

    @Getter
    private List<ExceptionMessage> messages;

    public enum MessageType{
        ERROR, WARNING, INFO
    }

    private ExceptionMessages(){
        messages = new ArrayList<>();
    }

    public static ExceptionMessagesBuilder builder(){
        return new ExceptionMessagesBuilder();
    }

    public MessageType getType(){
        if(messages == null){
            throw new IllegalStateException("Does not exist messages to determinate the type of global message");
        }else{
            long errors = messages.stream().filter(m -> m.getType() == MessageType.ERROR).count();
            long warn = messages.stream().filter(m -> m.getType() == MessageType.WARNING).count();

            if(errors > 0){
                return MessageType.ERROR;
            }else if(warn > 0){
                return MessageType.WARNING;
            } else {
                return MessageType.INFO;
            }
        }
    }

    /**
     * Builder of ExceptionMessage
     */
    public static class ExceptionMessagesBuilder {
        private ExceptionMessages exceptionMessages;

        public ExceptionMessagesBuilder() {
            exceptionMessages = new ExceptionMessages();
        }

        public ExceptionMessagesBuilder add(MessageType type, String message){
            exceptionMessages.messages.add(new ExceptionMessage(type, message));

            return this;
        }

        public ExceptionMessages build(){
            return exceptionMessages;
        }
    }
}
