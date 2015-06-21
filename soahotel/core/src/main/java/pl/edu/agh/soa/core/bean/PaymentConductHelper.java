package pl.edu.agh.soa.core.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Ala Czyz.
 */
@XmlRootElement
public class PaymentConductHelper {
        @XmlElement
        public String creditCard;
        @XmlElement public String bankName;
}
