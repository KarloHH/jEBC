package de.jebc.ebc.addressbook.activities;

import java.sql.Connection;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.addressdetails.GenerateAddressDTO;
import de.jebc.ebc.addressbook.domain.addressdetails.GenerateQueryForAddress;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.Board;

public class DisplayAddressDetails extends Board {

    private GenerateQueryForAddress query;
    private GenerateAddressDTO generate;
    private final ExecuteDatasource datasource;

    public DisplayAddressDetails(ExecuteDatasource datasource) {
        this.datasource = datasource;
        query = new GenerateQueryForAddress();
        generate = new GenerateAddressDTO();

        connect(query.Result(), datasource.StartQuery());
        connect(datasource.ResultQuery(), generate.Start());
    }

    public InPin<BaseAddressData> start() {
        return query.Start();
    }

    public OutPin<Address> result() {
        return generate.Result();
    }

    public QueryOutPin<Object, Connection> Connection() {
        return datasource.Connection();
    }
}
