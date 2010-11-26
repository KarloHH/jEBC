package de.jebc.ebc.addressbook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.QueryInPin;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.domain.AddressCategory;
import de.jebc.ebc.addressbook.domain.baseadresses.BaseAddressData;
import de.jebc.ebc.addressbook.domain.deleteaddress.DeleteAddressFromDatasource;
import de.jebc.ebc.addressbook.domain.deleteaddress.GenerateDeleteCommand;

public class TestDeleteAddressFromDatasource {

    private Connection conn;
    private boolean done;

    @Test
    public void generate() throws Exception {

        conn = getConnection();

        BaseAddressData address = new BaseAddressData(1,
                new AddressCategory(""), "");
        done = false;

        DeleteAddressFromDatasource sut = new DeleteAddressFromDatasource(new JdbcExecuteDatasourceQuery());

        sut.Result().connect(new InTrigger() {

            @Override
            public void receive() {
                done = true;
            }
        });

        sut.Connection().connect(new QueryInPin<Object, Connection>() {

            @Override
            public void receive(Object message, InPin<Connection> response) {
                response.receive(conn);
            }
        });

        sut.Start().receive(address);

        assertTrue(done);
        assertTrue(addressDeleted());
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name')");
        return conn;
    }

    private boolean addressDeleted() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Adressen");
        rs.next();
        int count = rs.getInt(1);
        return count == 0;
    }
}
