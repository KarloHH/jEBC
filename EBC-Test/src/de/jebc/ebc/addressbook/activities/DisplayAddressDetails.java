package de.jebc.ebc.addressbook.activities;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.addressdetails.GenerateAddressDTO;
import de.jebc.ebc.addressbook.domain.addressdetails.GenerateQueryForAddress;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.Board;

public class DisplayAddressDetails extends Board {

    private GenerateQueryForAddress query;
    private GenerateAddressDTO generate;

    public DisplayAddressDetails(ExecuteDatasource datasource) {
        query = new GenerateQueryForAddress();
        generate = new GenerateAddressDTO();

        connect(query.Result(), datasource.StartQuery());
        connect(datasource.ResultQuery(), generate.Start());
    }

    public InPin<BaseAddressData> start() {
        return query.Start();
    }

    public OutPin<Address> Result() {
        return generate.Result();
    }

}
