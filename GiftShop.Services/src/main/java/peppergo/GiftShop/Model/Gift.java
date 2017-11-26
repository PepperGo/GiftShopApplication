/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gift")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int giftId ;
    
    @Column(name="giftname")
    private String giftName;
    
    @Column(name="category")
    private String category;
    
    @Column(name="price")
    private double price;
    
    @Column(name="companyName")
    private String companyName;

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
    
}
