package de.jebc.ebc.addressbook.domain.deleteaddress;

import java.sql.Connection;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.Board;

public class DeleteAddressFromDatasource extends Board {

    private GenerateDeleteCommand generate;
    private final ExecuteDatasource query;

    public DeleteAddressFromDatasource(ExecuteDatasource query) {
        this.query = query;
        generate = new GenerateDeleteCommand();
        connect(generate.Result(), query.StartCommand());
    }

    public OutTrigger Result() {
        return query.CommandDone();
    }

    public QueryOutPin<Object, Connection> Connection() {
        return query.Connection();
    }

    public InPin<BaseAddressData> Start() {
        return generate.Start();
    }

}
