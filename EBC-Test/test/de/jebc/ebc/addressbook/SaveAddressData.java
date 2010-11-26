package de.jebc.ebc.addressbook;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.impl.Board;

public class SaveAddressData extends Board {

    private GenerateUpdateQuery generate;
    private final ExecuteDatasource datasourceQuery;

    public SaveAddressData(ExecuteDatasource datasourceQuery) {
        this.datasourceQuery = datasourceQuery;
        generate = new GenerateUpdateQuery();
        
        connect(generate.Result(), datasourceQuery.StartCommand());
    }

    public InPin<Address> Start() {
        return generate.Start();
    }

    public OutPin<Object> Result() {
        return datasourceQuery.CommandDone();
    }

    public QueryOutPin<Object, java.sql.Connection> Connection() {
        return datasourceQuery.Connection();
    }

}
