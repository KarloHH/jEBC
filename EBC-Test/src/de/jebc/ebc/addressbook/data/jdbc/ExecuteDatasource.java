package de.jebc.ebc.addressbook.data.jdbc;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.Query;
import de.jebc.ebc.addressbook.data.Resultset;

public interface ExecuteDatasource {

    public abstract InPin<Query> StartQuery();
    
    public abstract OutPin<Resultset> ResultQuery();
    
    public abstract InPin<Query> StartCommand();
    
    public abstract OutPin<Void> CommandDone();

    public abstract InPin<Query> StartIdentity();

    public abstract OutPin<Integer> ResultIdentity();
}