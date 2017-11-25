/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Bean;


public class RegistrationBean {
    private String name;
    private String password;
    /**
     * Default constructor. 
     */
    public RegistrationBean() {
        name = "";
        password = "";
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
