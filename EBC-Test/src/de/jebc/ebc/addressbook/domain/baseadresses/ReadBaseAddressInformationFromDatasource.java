package de.jebc.ebc.addressbook.domain.baseadresses;

import java.sql.Connection;
import java.util.List;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasourceQuery;
import de.jebc.ebc.addressbook.data.jdbc.JdbcExecuteDatasourceQuery;
import de.jebc.ebc.impl.Board;

public class ReadBaseAddressInformationFromDatasource extends Board {
    
    private InPin<Object> startPin;
    private QueryOutPin<Object, Connection> connectionPin;
    private OutPin<List<BaseAddressData>> resultPin;

    public ReadBaseAddressInformationFromDatasource(JdbcExecuteDatasourceQuery jdbcExecuteDatasourceQuery) {
        GenerateQueryForAllBaseAdresses generateQuery = new GenerateQueryForAllBaseAdresses();
        ExecuteDatasourceQuery execute = jdbcExecuteDatasourceQuery;
        GenerateBaseAddressDTOs generateDTOs = new GenerateBaseAddressDTOs();
        //
        startPin = extend(generateQuery.start());
        connect(generateQuery.accessDatasource(), execute.Query());
        connectionPin = extend(execute.GetConnection());
        connect(execute.Result(), generateDTOs.start());
        resultPin = extend(generateDTOs.addresses());
    }
    
    public InPin<Object> start() {
        return startPin;
    }
    
    public QueryOutPin<Object, Connection> connection() {
        return connectionPin;
    }
    
    public OutPin<List<BaseAddressData>> result() {
        return resultPin;
    }

}
