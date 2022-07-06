package com.example.cs195tennis.VerifyDataIntegrity;

import java.io.IOException;

import static java.lang.System.out;
import static org.jooq.impl.DSL.query;

public class VerifyDataIntegrity {


    public static void main(String[] args) throws IOException {
        //===============================================
        //              Connection to MySql Db 'sakila'
        //===============================================
        VerifySakilaDB sakilaTests = new VerifySakilaDB();

        int test = 3;
        //===============================================
        //              Verify Data Sakila DB
        //===============================================
        switch (test) {
            case 1:
                sakilaTests.verifyCurrentSchema();
            case 2:
                sakilaTests.verifyTablesInDatabase();
            case 3:
                sakilaTests.verifyAggregateAfterGroupBy();
            case 4:
                sakilaTests.verifyConsumingLargeRecords();
            case 5:
                sakilaTests.verifyTypeSafeActiveRecord();
            case 6:
                sakilaTests.verifyTypeSafeUnion();
            case 7:
                sakilaTests.verifyTypeSafetyWithInPredicate();
            case 8:
                sakilaTests.verifyStandardisationLimit();
        }

//
//        ===============================================
//                      Verify Data Atp Tour
//        ===============================================
//
//        ===============================================
//                      Verify Data Wta Tour
//        ===============================================
    }
}



