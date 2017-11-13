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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import peppergo.GiftShop.Dao.UserDao;
import peppergo.GiftShop.Model.User;

@Path("/loginservices")
public class LoginServices {
    
    @Path("/checkuservalidity")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public Response isValidUser(MultivaluedMap<String, String> formParam) {
        boolean response = false;
        User temp = new User();
        temp.setUserName(formParam.getFirst("username"));
        temp.setPassword(formParam.getFirst("password"));
        
        
        UserDao dao = new UserDao(); 
        List<User> userList = new ArrayList(dao.getUsers());
        
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUserName().equals(temp.getUserName()) && 
                    userList.get(i).getPassword().equals(temp.getPassword())){
                response = true;
                Response.ok().entity(String.valueOf(response)).build();
            }        
        }
        /*
        if(formParam.getFirst("password").equals("admin")){
            response = true;
        }
        else{
            response = false;
        }
        */
        
        return Response.ok().entity(String.valueOf(response)).build();
    }
    
    @Path("/availableusername/{username}")
    @GET
    public String availableUsername(@PathParam("username") String username) {
        
        return username + "001";
    }
}

