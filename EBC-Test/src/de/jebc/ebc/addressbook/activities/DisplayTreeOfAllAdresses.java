package de.jebc.ebc.addressbook.activities;

import java.sql.Connection;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.ConvertAddressesIntoTreeStructure;
import de.jebc.ebc.addressbook.domain.baseadresses.ReadBaseAddressInformationFromDatasource;
import de.jebc.ebc.impl.Board;

public class DisplayTreeOfAllAdresses extends Board {

    private ReadBaseAddressInformationFromDatasource read;
    private ConvertAddressesIntoTreeStructure convert;

    public DisplayTreeOfAllAdresses(ExecuteDatasource datasource) {
        read = new ReadBaseAddressInformationFromDatasource(datasource);
        convert = new ConvertAddressesIntoTreeStructure();
        //
        connect(read.Result(), convert.Start());
    }

    public InTrigger start() {
        return read.Start();
    }

    public OutPin<TreeModel> tree() {
        return convert.Result();
    }

    public QueryOutPin<Object, Connection> connection() {
        return read.Connection();
    }
}
