package de.jebc.ebc.addressbook.activities;

import javax.swing.tree.TreeModel;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.ConvertAddressesIntoTreeStructure;
import de.jebc.ebc.addressbook.domain.baseadresses.ReadBaseAddressInformationFromDatasource;
import de.jebc.ebc.impl.Board;
import de.jebc.thread.SwitchToEDT;

public class DisplayTreeOfAllAdresses extends Board {

    private ReadBaseAddressInformationFromDatasource read;
    private ConvertAddressesIntoTreeStructure convert;
    private SwitchToEDT<TreeModel> switchToForeground;

    public DisplayTreeOfAllAdresses(ExecuteDatasource datasource) {
        read = new ReadBaseAddressInformationFromDatasource(datasource);
        convert = new ConvertAddressesIntoTreeStructure();
        switchToForeground = new SwitchToEDT<TreeModel>();
        //
        connect(read.Result(), convert.Start());
        connect(convert.Result(), switchToForeground.Start());
    }

    public InTrigger Start() {
        return read.Start();
    }

    public OutPin<TreeModel> Tree() {
        return switchToForeground.Result();
    }

}
