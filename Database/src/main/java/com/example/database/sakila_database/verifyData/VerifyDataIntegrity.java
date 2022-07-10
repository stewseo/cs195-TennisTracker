package com.example.database.sakila_database.verifyData;


import com.example.database.MyCatalog;
import org.jooq.DSLContext;

public class VerifyDataIntegrity {
    public static void main(String[] args) throws Exception {
        //=====================================================
        //              Connection to MySql Db 'sakila'
        //=====================================================
        VerifySakilaDB sakilaTests = new VerifySakilaDB("sakila");
        String schema = "sakila";

        int verification = 1;
        //===============================================================
        //              Verify Data integrity Sakila DB
        //===============================================================
        switch (verification) {
            case 1 -> sakilaTests.verifySchema("sakila");
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



