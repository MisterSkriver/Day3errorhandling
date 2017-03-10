package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class QuoteNotFoundExceptionMapper implements ExceptionMapper<QuoteNotFoundException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context 
    ServletContext context;
    
    @Override
    public Response toResponse(QuoteNotFoundException ex) {
        return Response.status(404).entity(ex.getMessage()).build();
    }
    
    
    
    
    
//    public QuoteNotFoundExceptionMapper() {
//    }

//    public QuoteNotFoundExceptionMapper(String msg) {
//        super(msg);
//    }

}
