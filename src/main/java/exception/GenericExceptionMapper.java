package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(404).entity(exception.getMessage()).build();
    }
}