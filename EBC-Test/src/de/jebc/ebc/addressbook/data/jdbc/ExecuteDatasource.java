package de.jebc.ebc.addressbook.data.jdbc;

import java.sql.Connection;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.OutTrigger;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;

public interface ExecuteDatasource {

    public abstract InPin<Query> StartQuery();
    
    public abstract OutPin<Resultset> ResultQuery();
    
    public abstract InPin<Query> StartCommand();
    
    public abstract OutTrigger CommandDone();

    public abstract InPin<Query> StartIdentity();

    public abstract OutPin<Integer> ResultIdentity();
}