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

    private InPin<Object> startPin;
    private OutPin<TreeModel> treePin;
    private QueryOutPin<Object, Connection> connectionPin;

    public DisplayTreeOfAllAdresses(ExecuteDatasourceQuery datasource) {
        ReadBaseAddressInformationFromDatasource read = new ReadBaseAddressInformationFromDatasource(
                datasource);
        ConvertAddressesIntoTreeStructure convert = new ConvertAddressesIntoTreeStructure();
        //
        startPin = extend(read.start());
        connect(read.result(), convert.convert());
        treePin = extend(convert.tree());
        connectionPin = extend(read.connection());
    }

    public InPin<Object> start() {
        return startPin;
    }

    public OutPin<TreeModel> tree() {
        return treePin;
    }

    public QueryOutPin<Object, Connection> connection() {
        return connectionPin;
    }
}
