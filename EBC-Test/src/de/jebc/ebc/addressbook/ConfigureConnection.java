package de.jebc.ebc.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConfigureConnection {

    public static Connection Configure(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        String dbname = args.length > 0 ? args[0] : "/home/olaf/test.db";
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"
                + dbname);
        checkTables(connection);
        return connection;
    }

    private static void checkTables(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Adressen (ID INTEGER PRIMARY KEY AUTOINCREMENT, Category TEXT, Name TEXT, GivenName TEXT,"
                + "ZipCode TEXT, City TEXT, Street TEXT, Country TEXT);");
    }

}
