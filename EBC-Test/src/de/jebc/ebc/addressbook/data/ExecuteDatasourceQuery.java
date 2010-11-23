package de.jebc.ebc.addressbook.data;

import java.sql.Connection;

import de.jebc.ebc.Process;
import de.jebc.ebc.QueryOutPin;

public interface ExecuteDatasourceQuery extends Process<Query, Resultset> {

    public abstract QueryOutPin<Object, Connection> Connection();

}