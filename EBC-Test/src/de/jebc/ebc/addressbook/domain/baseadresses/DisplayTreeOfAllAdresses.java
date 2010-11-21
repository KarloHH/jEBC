package de.jebc.ebc.addressbook.domain.baseadresses;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.ConvertAddressesIntoTreeStructure;
import de.jebc.ebc.impl.Board;

public class DisplayTreeOfAllAdresses extends Board {
    
    private InPin<Object> startPin;
    private OutPin<TreeModel> treePin;

    public DisplayTreeOfAllAdresses(ExecuteDatasourceQuery datasource) {
        ReadBaseAddressInformationFromDatasource read = new ReadBaseAddressInformationFromDatasource(datasource);
        ConvertAddressesIntoTreeStructure convert = new ConvertAddressesIntoTreeStructure();
        //
        startPin = extend(read.start());
        connect(read.result(), convert.convert());
        treePin = extend(convert.tree());
    }

    public InPin<Object> start() {
        return startPin;
    }
    
    public OutPin<TreeModel> tree() {
        return treePin;
    }
}
