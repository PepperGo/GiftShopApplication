/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import peppergo.GiftShop.Model.Administrator;



public class AdminDao {
    
    public void addAdmin(Administrator bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addAdmin(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addAdmin(Session session, Administrator bean){
        Administrator newAdmin = new Administrator();
        //newAdmin.setAdminId(bean.getAdminId());
        newAdmin.setAdminName(bean.getAdminName());
        newAdmin.setPassword(bean.getPassword());
        
        session.save(newAdmin);
    }
    
    public List<Administrator> getAdmins(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Administrator");
        List<Administrator> adminList =  query.list();
        session.close();
        return adminList;
    }
 
    public int deleteAdmin(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Administrator where adminId = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public boolean isValidAdmin(Administrator admin){
        List<Administrator> adminList = new ArrayList<Administrator>(this.getAdmins());
        String name = admin.getAdminName();
        String password = admin.getPassword();
        for(int i = 0; i < adminList.size(); i++){
            if(adminList.get(i).getAdminName().equals(name) && adminList.get(i).getPassword().equals(password))
                return true;
        }
        return false;
    }
    
}
