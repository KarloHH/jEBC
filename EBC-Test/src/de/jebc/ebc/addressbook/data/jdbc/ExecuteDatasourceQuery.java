package de.jebc.ebc.addressbook.data.jdbc;

import java.sql.Connection;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;

public interface ExecuteDatasourceQuery {

    public abstract OutPin<Resultset> Result();

    public abstract QueryOutPin<Object, Connection> GetConnection();

    public abstract InPin<Query> Query();

}