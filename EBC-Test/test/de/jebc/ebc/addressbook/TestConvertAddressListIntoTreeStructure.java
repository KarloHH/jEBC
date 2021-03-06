package de.jebc.ebc.addressbook;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.ConvertAddressesIntoTreeStructure;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;

public class TestConvertAddressListIntoTreeStructure {

    TreeModel result = null;

    @Test
    public void setupInterface() {
        List<BaseAddressData> input = new ArrayList<BaseAddressData>();
        input.add(new BaseAddressData(1, new AddressCategory("Privat"),
                "Meyer, Hans"));
        input.add(new BaseAddressData(2, new AddressCategory("Büro"),
                "Franzen, Franz"));
        input.add(new BaseAddressData(3, new AddressCategory("Privat"),
                "Pitt, Brad"));
        input.add(new BaseAddressData(4, new AddressCategory("Privat"),
                "Jolie, Angelina"));
        input.add(new BaseAddressData(5, new AddressCategory("Büro"),
                "Merkel, Angela"));

        ConvertAddressesIntoTreeStructure sut = new ConvertAddressesIntoTreeStructure();
        InPin<TreeModel> pin = new InPin<TreeModel>() {

            @Override
            public void receive(TreeModel message) {
                result = message;
            }

        };
        sut.Result().connect(pin);

        sut.Start().receive(input);

        assertEquals(2, result.getChildCount(result.getRoot()));
        Object node1 = result.getChild(result.getRoot(), 0);
        assertEquals(3, result.getChildCount(node1));
        Object node2 = result.getChild(result.getRoot(), 1);
        assertEquals(2, result.getChildCount(node2));
    }

}
