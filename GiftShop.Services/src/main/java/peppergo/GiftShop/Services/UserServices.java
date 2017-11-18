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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import peppergo.GiftShop.Dao.UserDao;
import peppergo.GiftShop.Model.User;


@Path("/userservices")
public class UserServices {
    
    /*
    @Path("/userlists")
    @GET
    @Produces("application/json")
    public List<User> getUser() {
        UserDao dao = new UserDao();
        List Users = dao.getUsers();
        return Users;
    }
    */
    
    @Path("/userlists")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getUserList(){
        UserDao dao = new UserDao();
        List<User> usersList = dao.getUsers();
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(new ArrayList<User> (usersList)){};
        //String name = usersList.get(0).getUserName();
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(list).build();
    }
 
    
    @Path("/create")
    @POST
    @Consumes("application/json")
    public Response addUser(User user){
        
        user.setUserId(user.getUserId());
        user.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
                
        UserDao dao = new UserDao();
        dao.addUser(user);
        
        return Response.ok().build();
    }
    
    /*
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateUser(@PathParam("id") int id, User user){
        UserDao dao = new UserDao();
        int count = dao.updateUser(id, user);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteUser(@PathParam("id") int id){
        UserDao dao = new UserDao();
        int count = dao.deleteUser(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    */
    @Path("/test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "User Services running!";
    }
    
}
