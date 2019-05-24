package ie.dpd.shipping.controllers;

import ie.dpd.shipping.model.Address;
import ie.dpd.shipping.model.EircodeLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/")
public class EircodeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String QUERY_ALL_EIRCODES_STMT = "select * from eircodes";
    private final String INSERT_EIRCODE_STMT = "insert into eircodes(eircode,address) values(?,?)";
    private final String DELETE_EIRCODE_STMT = "delete from eircodes where eircode=?";

    @GetMapping(value = "/eircode", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EircodeLookup getEircodes() {
        return jdbcTemplate.query(QUERY_ALL_EIRCODES_STMT, rs -> {
            final List<Address> addresses = new ArrayList<>(2);

            while (rs.next()) {
                addresses.add(new Address(rs.getString("eircode"), rs.getString("address")));
            }

            EircodeLookup eircodeLookupObj = new EircodeLookup();
            eircodeLookupObj.setAddress(addresses);
            return eircodeLookupObj;
        });
    }

    @PutMapping(value = "/eircode")
    public ResponseEntity<String> putEircodeMapping(@RequestParam String eircode, @RequestParam String address) {
        int affectedRows = jdbcTemplate.update(INSERT_EIRCODE_STMT, eircode, address);

        if (affectedRows < 1) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eircode")
    public ResponseEntity<String> deleteEircodeMapping(@RequestParam String eircode) {
        int affectedRows = jdbcTemplate.update(DELETE_EIRCODE_STMT, eircode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
