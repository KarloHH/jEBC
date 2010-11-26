package de.jebc.ebc.addressbook.activities;

import java.sql.Connection;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.domain.deleteaddress.GenerateDeleteCommand;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.impl.QueryOutConnector;

public class DeleteCurrentAddress extends Board {

    private QueryOutConnector<Object, Connection> connectionPin = new QueryOutConnector<Object, Connection>();
    private DisplayTreeOfAllAdresses display;
    private GenerateDeleteCommand delete;

    public DeleteCurrentAddress(ExecuteDatasourceQuery query) {
        delete = new GenerateDeleteCommand();
        display = new DisplayTreeOfAllAdresses(query);

        connect(delete.Result(), display.start());
        connect(delete.Connection(), connectionPin.in());
        connect(display.connection(), connectionPin.in());
    }

    public QueryOutPin<Object, Connection> connection() {
        return connectionPin;
    }

    public InPin<BaseAddressData> in() {
        return delete.Start();
    }

    public OutPin<TreeModel> tree() {
        return display.tree();
    }
}
