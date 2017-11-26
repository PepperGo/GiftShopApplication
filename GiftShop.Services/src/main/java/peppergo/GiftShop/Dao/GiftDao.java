/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import peppergo.GiftShop.Model.Gift;

public class GiftDao {
    
    public void addGift(Gift bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addGift(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addGift(Session session, Gift bean){
        Gift newGift = new Gift();

        newGift.setGiftName(bean.getGiftName());
        newGift.setCompanyName(bean.getCompanyName());
        newGift.setCategory(bean.getCategory());
        newGift.setPrice(bean.getPrice());
        
        session.save(newGift);
    }
    
    public List<Gift> getGifts(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Gift");
        List<Gift> giftList =  query.list();
        session.close();
        return giftList;
    }
    
}

