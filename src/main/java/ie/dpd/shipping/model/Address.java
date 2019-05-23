package ie.dpd.shipping.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@JacksonXmlRootElement(localName = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    private String eircode;
    private String address;

    public Address(String eircode, String address) {
        this.eircode = eircode;
        this.address = address;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
