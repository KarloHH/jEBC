package de.jebc.ebc.addressbook.domain.baseadresses;

import java.util.List;

import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.data.jdbc.ExecuteDatasource;
import de.jebc.ebc.impl.Board;

public class ReadBaseAddressInformationFromDatasource extends Board {

    private GenerateQueryForAllBaseAdresses generateQuery;
    private GenerateBaseAddressDTOs generateDTOs;

    public ReadBaseAddressInformationFromDatasource(
            ExecuteDatasource datasource) {
        generateQuery = new GenerateQueryForAllBaseAdresses();
        generateDTOs = new GenerateBaseAddressDTOs();
        //
        connect(generateQuery.Result(), datasource.StartQuery());
        connect(datasource.ResultQuery(), generateDTOs.Start());
    }

    public InTrigger Start() {
        return generateQuery.Start();
    }

    public OutPin<List<BaseAddressData>> Result() {
        return generateDTOs.Result();
    }

}
