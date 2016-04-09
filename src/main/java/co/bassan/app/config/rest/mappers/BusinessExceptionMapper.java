package co.bassan.app.config.rest.mappers;

import co.bassan.app.config.exceptions.BusinessException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper to manage the BusinessException when is throw from the manager
 * to a rest response.
 *
 * @author pbastidas
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getExceptionMessages())
                .build();
    }
}
