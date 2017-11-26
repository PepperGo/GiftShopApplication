/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import peppergo.GiftShop.Dao.GiftDao;
import peppergo.GiftShop.Model.Gift;

@Path("/giftservices")
public class GiftServices {
    
    
    @Path("/giftlists")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getGiftList(){
        GiftDao dao = new GiftDao();
        List<Gift> giftsList = dao.getGifts();
        GenericEntity<List<Gift>> list = new GenericEntity<List<Gift>>(new ArrayList<Gift> (giftsList)){};
        //String name = usersList.get(0).getUserName();
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(list).build();
    }
 
   
    @Path("/test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Gift Services running!";
    }
    
}