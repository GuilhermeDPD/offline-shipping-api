package ie.dpd.shipping.controllers;

import ie.dpd.shipping.model.Address;
import ie.dpd.shipping.model.EircodeLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/")
public class EircodeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String ALL_EIRCODES_QUERY = "select * from eircodes";

    @GetMapping(value = "/eircode/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EircodeLookup getEircodes() {
        return jdbcTemplate.query(ALL_EIRCODES_QUERY, new ResultSetExtractor<EircodeLookup>() {
            @Override
            public EircodeLookup extractData(ResultSet rs) throws SQLException, DataAccessException {
                final List<Address> addresses = new ArrayList<>(2);

                while (rs.next()) {
                    addresses.add(new Address(rs.getString("eircode"), rs.getString("address")));
                }

                EircodeLookup eircodeLookupObj = new EircodeLookup();
                eircodeLookupObj.setAddress(addresses);
                return eircodeLookupObj;
            }
        });
    }
}
