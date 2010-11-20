package de.jebc.ebc.addressbook.data.baseadresses;

import java.util.ArrayList;
import java.util.List;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateBaseAddressDTOs {

    private OutPin<List<BaseAddressData>> outPin = new SingleOutPin<List<BaseAddressData>>();
    private InPin<Resultset> inPin = new InPin<Resultset>() {

        @Override
        public void receive(Resultset message) {
            List<BaseAddressData> result = generate(message);
            outPin.send(result);
        }
    };

    public OutPin<List<BaseAddressData>> addresses() {
        return outPin;
    }

    public InPin<Resultset> start() {
        return inPin;
    }

    protected List<BaseAddressData> generate(Resultset rs) {
        List<BaseAddressData> result = new ArrayList<BaseAddressData>();
        while(rs.next()) {
            result.add(buildBaseAddress(rs));
        }
        return result;
    }

    private BaseAddressData buildBaseAddress(Resultset rs) {
        BaseAddressData result = new BaseAddressData(
                rs.getInt("ID"),
                rs.getString("Category"),
                rs.getString("GivenName"),
                rs.getString("Name")
                );
        return result;
    }

}
