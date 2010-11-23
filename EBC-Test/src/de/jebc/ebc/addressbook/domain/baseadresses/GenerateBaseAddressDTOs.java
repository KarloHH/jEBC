package de.jebc.ebc.addressbook.domain.baseadresses;

import java.util.ArrayList;
import java.util.List;

import de.jebc.ebc.addressbook.data.DataException;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.impl.ProcessImpl;

public class GenerateBaseAddressDTOs extends ProcessImpl<Resultset, List<BaseAddressData>> {

    protected void process(Resultset message) {
        List<BaseAddressData> result = generate(message);
        Result().send(result);
    }

    private List<BaseAddressData> generate(Resultset rs) {
        List<BaseAddressData> result = new ArrayList<BaseAddressData>();
        try {
            while (rs.next()) {
                result.add(buildBaseAddress(rs));
            }
        } catch (DataException e) {
            // TODO exception handling
            e.printStackTrace();
        }
        return result;
    }

    private BaseAddressData buildBaseAddress(Resultset rs) throws DataException {
        BaseAddressData result = new BaseAddressData(rs.getInt("ID"),
                new AddressCategory(rs.getString("Category")),
                rs.getString("Name") + ", " + rs.getString("GivenName"));
        return result;
    }

}
