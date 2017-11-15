/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import peppergo.GiftShop.Model.User;

public class UserDao {
    
    public void addUser(User bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addUser(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addUser(Session session, User bean){
        User newUser = new User();
        newUser.setUserId(bean.getUserId());
        newUser.setUserName(bean.getUserName());
        newUser.setPassword(bean.getPassword());
        
        session.save(newUser);
    }
    
    public List<User> getUsers(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from User");
        List<User> userList =  query.list();
        session.close();
        return userList;
    }
 
    public int deleteUser(String id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from User where id = :id";
        Query query = session.createQuery(hql);
        query.setString("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public boolean isValidUser(User user){
        List<User> userList = new ArrayList<User>(this.getUsers());
        String name = user.getUserName();
        String password = user.getPassword();
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUserName().equals(name) && userList.get(i).getPassword().equals(password))
                return true;
        }
        return false;
    }
    
    /*
    public int updateUser(int id, User emp){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update User set name = :name, age=:age where id = :id";
            Query query = session.createQuery(hql);
            query.setString("id",id);
            query.setString("name",emp.getName());
            query.setString("password",emp.getAge());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
    */
    
}
