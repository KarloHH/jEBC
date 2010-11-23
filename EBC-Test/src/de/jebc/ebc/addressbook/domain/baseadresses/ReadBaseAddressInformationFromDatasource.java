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
        connect(generateQuery.Result(), execute.Start());
        connect(execute.Result(), generateDTOs.Start());
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
