package de.jebc.ebc.addressbook.activities;

import java.sql.Connection;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.addressbook.domain.savenewaddress.GenerateInsertCommand;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.impl.Joiner;
import de.jebc.ebc.impl.PinBroadcast;
import de.jebc.ebc.impl.StartTrigger;
import de.jebc.ebc.impl.StopTrigger;

public class SaveNewAddressData extends Board {

    private final ExecuteDatasource dataSource;
    private GenerateInsertCommand generate;
    private StartTrigger<Address> start;
    private PinBroadcast<Address> broadcast;
    private Joiner<Address, Integer, Address> join;
    private StopTrigger<Address> stop;

    public SaveNewAddressData(ExecuteDatasource dataSource) {
        this.dataSource = dataSource;
        generate = new GenerateInsertCommand();
        createHelperParts();
        // wiring
        connect(start.Trigger(), join.Reset());
        connect(start.Result(), broadcast.In());
        connect(broadcast.Out(), join.In1());
        connect(broadcast.Out(), generate.Start());
        connect(generate.Result(), dataSource.StartIdentity());
        connect(dataSource.ResultIdentity(), join.In2());
        connect(join.Out(), stop.Start());
    }

    private void createHelperParts() {
        start = new StartTrigger<Address>();
        broadcast = new PinBroadcast<Address>();
        join = new Joiner<Address, Integer, Address>() {

            @Override
            protected Address join(Address in1, Integer in2) {
                Address result = new Address(in2, in1.getCategory(),
                        in1.getName(), in1.getGivenName(), in1.getZipCode(),
                        in1.getCity(), in1.getStreet(), in1.getCountry());
                return result;
            }
        };
        stop = new StopTrigger<Address>();
    }

    public OutPin<Object> Completed() {
        return stop.Trigger();
    }

    public QueryOutPin<Object, Connection> Connection() {
        return dataSource.Connection();
    }

    public OutPin<Address> Result() {
        return stop.Result();
    }

    public InPin<Address> Start() {
        return start.Start();
    }

}
