/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;





import peppergo.GiftShop.Bean.LoginBean;
import peppergo.GiftShop.Bean.User;


/**
 * Servlet implementation class SessionControllerServlet
 */
@WebServlet("/UserInformationServlet")
public class UserInformationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        //int userId=Integer.parseInt(request.getParameter("userId"));
        
        Boolean status = false;
        try {
             
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/GiftShopServices/userservices/user");

            ClientResponse restResponse = webResource.path("/1")
                .type("application/json")
                .get(ClientResponse.class);
            
            if (restResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
            }
            
            String userInfo = restResponse.getEntity(String.class);

          
           // out.write(userInfo);
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(userInfo).getAsJsonObject();

            
            //out.write(jsonObj.get("email").getAsString());
           
       
            
            HttpSession session = request.getSession();
            session.setAttribute("userId", jsonObj.get("userId").getAsInt());
            session.setAttribute("userdata", jsonObj);
            RequestDispatcher rd=request.getRequestDispatcher("userInformation.jsp");
            rd.forward(request, response);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        if(status){
            HttpSession session = request.getSession();
            session.setAttribute("USER", name);
            RequestDispatcher rd=request.getRequestDispatcher("welcome-page.jsp");
            rd.forward(request, response);
            
        }
        else{
            RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
            rd.forward(request, response);
        }
        */
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
       // int userId=Integer.parseInt(request.getParameter("userId"));
        
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        
        LoginBean bean=new LoginBean();
        bean.setName(name);
        bean.setPassword(password);
        request.setAttribute("bean",bean);

        Boolean status = false;
        try {
             
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/GiftShopServices/loginservices/checkuservalidity");
            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("username", name);
            formData.add("password", password);
            ClientResponse restResponse = webResource
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, formData);
            
            if (restResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
            }
 
            String statusString = restResponse.getEntity(String.class);
            status = Boolean.parseBoolean(statusString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(status){
            HttpSession session = request.getSession();
            session.setAttribute("USER", name);
            RequestDispatcher rd=request.getRequestDispatcher("welcome-page.jsp");
            rd.forward(request, response);
            
        }
        else{
            RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
            rd.forward(request, response);
        }
    }

}

