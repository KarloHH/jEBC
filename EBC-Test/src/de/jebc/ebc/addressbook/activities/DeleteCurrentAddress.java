package de.jebc.ebc.addressbook.activities;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.domain.deleteaddress.DeleteAddressFromDatasource;
import de.jebc.ebc.impl.Board;

public class DeleteCurrentAddress extends Board {

    private DisplayTreeOfAllAdresses display;
    private DeleteAddressFromDatasource delete;

    public DeleteCurrentAddress(ExecuteDatasource query) {
        delete = new DeleteAddressFromDatasource(query);
        display = new DisplayTreeOfAllAdresses(query);

        connect(delete.Result(), display.start());
    }

    public InPin<BaseAddressData> in() {
        return delete.Start();
    }

    public OutPin<TreeModel> tree() {
        return display.tree();
    }
}
