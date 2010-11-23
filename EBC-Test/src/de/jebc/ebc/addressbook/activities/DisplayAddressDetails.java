package de.jebc.ebc.addressbook.activities;

import java.sql.Connection;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.addressdetails.GenerateAddressDTO;
import de.jebc.ebc.addressbook.domain.addressdetails.GenerateQueryForAddress;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.Board;

public class DisplayAddressDetails extends Board {

    private GenerateQueryForAddress query;
    private GenerateAddressDTO generate;
    private final ExecuteDatasourceQuery datasource;

    public DisplayAddressDetails(ExecuteDatasourceQuery datasource) {
        this.datasource = datasource;
        query = new GenerateQueryForAddress();
        generate = new GenerateAddressDTO();
        
        connect(query.accessDatasource(), datasource.Query());
        connect(datasource.Result(), generate.start());
    }

    public InPin<BaseAddressData> start() {
        return query.start();
    }

    public OutPin<Address> result() {
        return generate.result();
    }
    
    public QueryOutPin<Object, Connection> Connection() {
        return datasource.GetConnection();
    }
}
