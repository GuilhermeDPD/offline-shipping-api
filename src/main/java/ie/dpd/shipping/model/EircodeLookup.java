package ie.dpd.shipping.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@JacksonXmlRootElement(localName = "eircodeLookup")
@XmlAccessorType(XmlAccessType.FIELD)
public class EircodeLookup {
    @JacksonXmlElementWrapper(localName = "entry", useWrapping = false)
    List<Address> address = null;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> addresses) {
        this.address = addresses;
    }
}
