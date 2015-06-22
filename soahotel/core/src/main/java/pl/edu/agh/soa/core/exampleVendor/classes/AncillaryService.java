package pl.edu.agh.soa.core.exampleVendor.classes;


/**
 * Created by Ala Czyz.
 */
public class AncillaryService {
    String price;
    String id;
    String desc;

    public AncillaryService(String price, String id, String desc) {
        this.price = price;
        this.id = id;
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
