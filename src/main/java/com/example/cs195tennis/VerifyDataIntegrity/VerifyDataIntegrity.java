package com.example.cs195tennis.VerifyDataIntegrity;

import Database.MyCatalog;
import org.jooq.Catalog;

import java.io.IOException;

import static com.example.cs195tennis.VerifyDataIntegrity.VerifyDatabase.println;
import static org.jooq.impl.DSL.query;

public class VerifyDataIntegrity {

    public static void main(String[] args) throws Exception {
        //=====================================================
        //              Connection to MySql Db 'sakila'
        //=====================================================
        VerifySakilaDB sakilaTests = new VerifySakilaDB("sakila");

        String schema = MyCatalog.CATALOG.getSchema("sakila").getName();

        int verification = 11;
        //===============================================================
        //              Verify Data integrity Sakila DB
        //===============================================================
        switch (verification) {
            case 1 -> sakilaTests.verifySchema(schema);
            case 2 -> sakilaTests.verifyAggregateFunctions(schema);
            case 3 -> sakilaTests.verifyConsumingLargeRecords(schema);
            case 4 -> sakilaTests.verifyActiveRecords(schema);
            case 5 -> sakilaTests.verifyUnions(schema);
            case 6 -> sakilaTests.verifyWithInPredicates(schema);
            case 7 -> sakilaTests.verifyStandardisationLimit(schema);
            case 8 -> sakilaTests.verifyJoin(schema);
            case 9 -> sakilaTests.verifyAliasing(schema);
            case 10 -> sakilaTests.verifyImplicitJoins(schema);
            case 11 -> sakilaTests.verifyNestedRecords(schema);
            case 12 -> sakilaTests.testDynamicSql(schema);
            default -> println("default");
        }


//        ===============================================
//                      Verify Data Atp Tour
//        ===============================================
//
//        ===============================================
//                      Verify Data Wta Tour
//        ===============================================
    }
}



