package de.jebc.ebc.addressbook.domain.baseadresses;

import java.sql.Connection;
import java.util.List;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.QueryOutPin;
import de.jebc.ebc.addressbook.data.ExecuteDatasourceQuery;
import de.jebc.ebc.impl.Board;

public class ReadBaseAddressInformationFromDatasource extends Board {

    private GenerateQueryForAllBaseAdresses generateQuery;
    private ExecuteDatasourceQuery execute;
    private GenerateBaseAddressDTOs generateDTOs;

    public ReadBaseAddressInformationFromDatasource(
            ExecuteDatasourceQuery datasource) {
        generateQuery = new GenerateQueryForAllBaseAdresses();
        execute = datasource;
        generateDTOs = new GenerateBaseAddressDTOs();
        //
        connect(generateQuery.accessDatasource(), execute.Query());
        connect(execute.Result(), generateDTOs.start());
    }

    public InPin<Object> start() {
        return generateQuery.start();
    }

    public QueryOutPin<Object, Connection> connection() {
        return execute.GetConnection();
    }

    public OutPin<List<BaseAddressData>> result() {
        return generateDTOs.addresses();
    }

}
