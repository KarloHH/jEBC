package de.jebc.ebc.addressbook.domain.deleteaddress;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.jebc.ebc.InPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.impl.ProcessImpl;
import de.jebc.ebc.impl.QueryPinImpl;

public class GenerateDeleteCommand extends ProcessImpl<BaseAddressData, Object> {

    private QueryOutPin<Object, Connection> connectionPin = new QueryPinImpl<Object, Connection>();
    public QueryOutPin<Object, Connection> Connection() {
        return connectionPin;
    }

    protected void process(BaseAddressData message) {
        final String command = "DELETE FROM Adressen WHERE ID = " + message.getId();
        Connection().send(null, new InPin<Connection>() {

            @Override
            public void receive(Connection conn) {
                try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(command);
                Result().send(null);
                } catch (SQLException exc) {
                    // TODO handle exception
                    System.err.println(exc.toString());
                }
            }
        });
    }

}
