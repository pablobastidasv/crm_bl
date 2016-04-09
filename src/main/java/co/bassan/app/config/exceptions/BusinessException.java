package co.bassan.app.config.exceptions;

import lombok.Getter;

/**
 * Exception object to reference the business exceptions presented
 * on the normal flow of the application.
 *
 * @author pbastidas
 */
public class BusinessException extends RuntimeException {

    @Getter
    private ExceptionMessages exceptionMessages;

    public BusinessException(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }
}
