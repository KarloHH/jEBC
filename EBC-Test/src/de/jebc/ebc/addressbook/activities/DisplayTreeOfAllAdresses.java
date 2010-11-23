package de.jebc.ebc.addressbook.activities;

import java.sql.Connection;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.ConvertAddressesIntoTreeStructure;
import de.jebc.ebc.addressbook.domain.baseadresses.ReadBaseAddressInformationFromDatasource;
import de.jebc.ebc.impl.Board;

public class DisplayTreeOfAllAdresses extends Board {

    private ReadBaseAddressInformationFromDatasource read;
    private ConvertAddressesIntoTreeStructure convert;

    public DisplayTreeOfAllAdresses(ExecuteDatasourceQuery datasource) {
        read = new ReadBaseAddressInformationFromDatasource(
                datasource);
        convert = new ConvertAddressesIntoTreeStructure();
        //
        connect(read.result(), convert.convert());
    }

    public InPin<Object> start() {
        return read.start();
    }

    public OutPin<TreeModel> tree() {
        return convert.tree();
    }

    public QueryOutPin<Object, Connection> connection() {
        return read.connection();
    }
}
