package de.jebc.ebc.addressbook.data;

import java.sql.Connection;

public interface ConnectionFactory {

    public abstract Connection getConnection();
}
