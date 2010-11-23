package de.jebc.ebc.addressbook.domain.deleteaddress;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.QueryPinImpl;
import de.jebc.ebc.impl.SingleOutPin;

public class GenerateDeleteCommand {

    private OutPin<Object> outPin = new SingleOutPin<Object>();
    private QueryOutPin<Object, Connection> connectionPin = new QueryPinImpl<Object, Connection>();
    private InPin<BaseAddressData> deletePin = new InPin<BaseAddressData>() {

        @Override
        public void receive(BaseAddressData message) {
            delete(message);
        }
    };

    public OutPin<Object> out() {
        return outPin;
    }

    public QueryOutPin<Object, Connection> connection() {
        return connectionPin;
    }

    public InPin<BaseAddressData> delete() {
        return deletePin;
    }

    protected void delete(BaseAddressData message) {
        final String command = "DELETE FROM Adressen WHERE ID = " + message.getId();
        connection().send(null, new InPin<Connection>() {

            @Override
            public void receive(Connection conn) {
                try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(command);
                out().send(null);
                } catch (SQLException exc) {
                    // TODO handle exception
                    System.err.println(exc.toString());
                }
            }
        });
    }

}
