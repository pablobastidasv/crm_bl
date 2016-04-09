package co.bassan.app.config.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Objects;

/**
 * Interceptor implementation of Paginable Interface
 *
 * To correct work of this Interceptor, the Response of the method intercepted
 * must be an List instance and have a header named TOTAL_ROWS_HEADER.
 *
 * @author pbastidas
 */
@Interceptor
@Paginable
public class PaginatorInterceptor implements Serializable {

    public static final String TOTAL_ROWS_HEADER = "totalRows";

    @AroundInvoke
    public Object paginator(InvocationContext ctx) throws Exception {

        final Response response = (Response) ctx.proceed();

        final String headerString = response.getHeaderString(TOTAL_ROWS_HEADER);

        Objects.requireNonNull(headerString);

        final String jsonEntityStr = new ObjectMapper().writeValueAsString(response.getEntity());
        final JsonReader reader = Json.createReader(new StringReader(jsonEntityStr));

        final JsonArray entity = reader.readArray();

        final JsonObject build = Json.createObjectBuilder()
                .add("count", Long.parseLong(headerString))
                .add("result", entity)
                .build();

        return Response.status(response.getStatus()).entity(build).build();
    }

}
