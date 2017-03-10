package data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exception.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("quote")
public class QuoteResource {

    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };
    private Random random = new Random();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuoteResource
     */
    public QuoteResource() {
    }

    /**
     * Retrieves representation of an instance of data.QuoteResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getText(@PathParam("id") int id) throws QuoteNotFoundException {
        if(quotes.get(id)!=null){
            return id+":"+quotes.get(id);
        }
        throw new QuoteNotFoundException("1:No quote exists with the index "+id+". Please enter a number between 1-3.");
    }

    @GET
    @Path("/random")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRandom() {
        int quoteNumber = random.nextInt(quotes.size())+1;
        return quoteNumber+":"+quotes.get(quoteNumber);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addQuote(String inputtedQuote) {
        JsonParser parser = new JsonParser();
        JsonObject job = parser.parse(inputtedQuote).getAsJsonObject();
        String quoteValue = job.get("quote").getAsString();
        quotes.put(quotes.size()+1,quoteValue);
        return "Quote added: "+quoteValue;
    }
       
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String addPerson(String jsonPerson) {
//        JsonParser parser = new JsonParser();
//        JsonObject job = parser.parse(jsonPerson).getAsJsonObject();
//        String quoteValue = job.get("quote").getAsString();
//        Response res = Response.status(200).entity(quoteValue).build();
//        return quoteValue;
//    }
    
    
    /**
     * PUT method for updating or creating an instance of QuoteResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String replaceText(@PathParam("id") int id, String input) {
        JsonParser parser = new JsonParser();
        JsonObject job = parser.parse(input).getAsJsonObject();
        String quoteValue = job.get("quote").getAsString();
        quotes.replace(id, quoteValue);
        return "Quote edited. Modified quote reads: "+quotes.get(id);
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteText(@PathParam("id") int id) {
        String quoteToDelete = quotes.get(id);
        quotes.remove(id);
        quotes.remove(null);
        return "Quote deleted: "+quoteToDelete;
    }
}