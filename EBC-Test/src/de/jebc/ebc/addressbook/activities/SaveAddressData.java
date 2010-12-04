package de.jebc.ebc.addressbook.activities;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.saveaddress.GenerateUpdateQuery;
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

    public OutTrigger Result() {
        return datasourceQuery.CommandDone();
    }

}
