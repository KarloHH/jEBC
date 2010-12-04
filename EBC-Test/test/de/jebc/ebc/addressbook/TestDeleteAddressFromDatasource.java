package de.jebc.ebc.addressbook;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.addressbook.data.ConnectionFactory;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.domain.deleteaddress.DeleteAddressFromDatasource;

public class TestDeleteAddressFromDatasource {

    private boolean done;
    private ConnectionFactory conn;

    @Test
    public void generate() throws Exception {

        conn = getConnection();

        BaseAddressData address = new BaseAddressData(1,
                new AddressCategory(""), "");
        done = false;

        DeleteAddressFromDatasource sut = new DeleteAddressFromDatasource(new JdbcExecuteDatasourceQuery(conn));

        sut.Result().connect(new InTrigger() {

            @Override
            public void receive() {
                done = true;
            }
        });

        sut.Start().receive(address);

        assertTrue(done);
        assertTrue(addressDeleted());
    }

    private ConnectionFactory getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name')");
        return new ConnectionFactory() {
            
            @Override
            public Connection getConnection() {
                return conn;
            }
        };
    }

    private boolean addressDeleted() throws Exception {
        Statement stmt = conn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Adressen");
        rs.next();
        int count = rs.getInt(1);
        return count == 0;
    }
}
