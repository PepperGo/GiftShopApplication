/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import peppergo.GiftShop.Dao.UserDao;
import peppergo.GiftShop.Model.Gift;
import peppergo.GiftShop.Model.User;


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
    
    
    @Path("/createByForm")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addGift(MultivaluedMap<String, String> formParam) {
        boolean response = false;
        Gift temp = new Gift();
        temp.setCategory(formParam.getFirst("category"));
        temp.setCompanyName(formParam.getFirst("company"));
        temp.setGiftName(formParam.getFirst("giftname"));
        temp.setPrice(Double.parseDouble(formParam.getFirst("price")));
        
        
        GiftDao dao = new GiftDao(); 
        try{
            dao.addGift(temp);
            response = true;
            return Response.ok().entity(String.valueOf(response)).build();
        }catch(Exception e){
            return Response.ok().entity(String.valueOf(response)).build();
        }  
        /*
        if(formParam.getFirst("password").equals("admin")){
            response = true;
        }
        else{
            response = false;
        }
        */  
    }
    
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") int id){
        GiftDao dao = new  GiftDao();
        int count = dao.deleteGift(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
   
    @Path("/test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Gift Services running!";
    }
    
}