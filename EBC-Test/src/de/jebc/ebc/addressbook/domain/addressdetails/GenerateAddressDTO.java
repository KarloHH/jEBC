package de.jebc.ebc.addressbook.domain.addressdetails;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.DataException;
import de.jebc.ebc.addressbook.data.Resultset;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateAddressDTO {

        private InPin<Resultset> trigger = new InPin<Resultset>() {

            @Override
            public void receive(Resultset rs) {
                try {
                    Address dto = new Address(rs.getInt("ID"), 
                            new AddressCategory(rs.getString("Category")),
                            rs.getString("Name"), 
                            rs.getString("GivenName"), 
                            rs.getString("ZipCode"), 
                            rs.getString("City"), 
                            rs.getString("Street"), 
                            rs.getString("Country"));
                    result().send(dto);
                } catch (DataException e) {
                    // TODO handle exception
                    e.printStackTrace();
                }
            }
        };

        private OutPin<Address> outQuery = new SingleOutPin<Address>();

        public InPin<Resultset> start() {
            return trigger;
        }

        public OutPin<Address> result() {
            return outQuery;
        }
}
