package de.jebc.ebc.addressbook.activities;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.domain.deleteaddress.GenerateDeleteCommand;
import de.jebc.ebc.impl.Board;

public class DeleteCurrentAddress extends Board {

    private GenerateDeleteCommand generate;
    private final ExecuteDatasource query;

    public DeleteCurrentAddress(ExecuteDatasource query) {
        this.query = query;
        generate = new GenerateDeleteCommand();
        connect(generate.Result(), query.StartCommand());
    }

    public OutTrigger Result() {
        return query.CommandDone();
    }

    public InPin<BaseAddressData> Start() {
        return generate.Start();
    }

}
