package ie.dpd.shipping.controllers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ie.dpd.shipping.model.Address;
import ie.dpd.shipping.model.EircodeLookup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestEircodeController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void eircodeListingSucceeds() throws Exception {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("A12345", "67 Glasson Street"));
        addresses.add(new Address("B67891", "23 Coosan Park"));

        EircodeLookup lookup = new EircodeLookup();
        lookup.setAddress(addresses);

        XmlMapper xmlMapper = new XmlMapper();
        String expectedResult = xmlMapper.writeValueAsString(lookup);

        mvc.perform(get("/v1/eircode")
                .accept(MediaType.APPLICATION_XML)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().xml(expectedResult))
                .andReturn();
    }
}
