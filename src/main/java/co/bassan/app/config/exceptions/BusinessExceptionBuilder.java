package co.bassan.app.config.exceptions;

import java.util.Arrays;

/**
 * Builder pattern to encapsulate the creation of a the ExceptionBusiness
 *
 * @author pbastidas
 */
public class BusinessExceptionBuilder {

    private ExceptionMessages.ExceptionMessagesBuilder messagesBuilder;

    private BusinessExceptionBuilder(){
        messagesBuilder = ExceptionMessages.builder();
    }

    public static BusinessExceptionBuilder builder(){
        return new BusinessExceptionBuilder();
    }

    public BusinessException build(){
        final ExceptionMessages messages = messagesBuilder.build();

        if(messages.getMessages().isEmpty()){
            throw new IllegalStateException("Must have at least one message");
        }

        return new BusinessException(messages);
    }

    public boolean hasMessages(){
        return !messagesBuilder
                .build()
                .getMessages()
                .isEmpty();
    }

    public BusinessExceptionBuilder addMessage(ExceptionMessages.MessageType type, String message, Object... params){
        if(params != null && params.length > 0){
            message = String.format(message, params);
        }

        messagesBuilder.add(type, message);

        return this;
    }
}
