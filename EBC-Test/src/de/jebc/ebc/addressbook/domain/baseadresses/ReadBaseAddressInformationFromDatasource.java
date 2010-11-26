package de.jebc.ebc.addressbook.domain.baseadresses;

import java.sql.Connection;
import java.util.List;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.impl.Board;

public class ReadBaseAddressInformationFromDatasource extends Board {

    private GenerateQueryForAllBaseAdresses generateQuery;
    private ExecuteDatasource execute;
    private GenerateBaseAddressDTOs generateDTOs;

    public ReadBaseAddressInformationFromDatasource(
            ExecuteDatasource datasource) {
        generateQuery = new GenerateQueryForAllBaseAdresses();
        execute = datasource;
        generateDTOs = new GenerateBaseAddressDTOs();
        //
        connect(generateQuery.Result(), execute.StartQuery());
        connect(execute.ResultQuery(), generateDTOs.Start());
    }

    public InPin<Object> Start() {
        return generateQuery.Start();
    }

    public QueryOutPin<Object, Connection> Connection() {
        return execute.Connection();
    }

    public OutPin<List<BaseAddressData>> Result() {
        return generateDTOs.Result();
    }

}
