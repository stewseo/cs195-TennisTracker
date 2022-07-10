package com.example.database.sakila_database.verifyData;


import org.jooq.Schema;

public class VerifyDataIntegrity {
    private static String currentSchemaName;
    public static void main(String[] args) throws Exception {
        //=====================================================
        //              Connection to MySql Db 'sakila'
        //=====================================================
        VerifySakilaDB sakilaTests = new VerifySakilaDB("sakila");

        Schema schema = sakilaTests.getSchema("sakila");

        currentSchemaName = schema.getName();

        int verification = 13;
        //===============================================================
        //              Verify Data integrity Sakila DB
        //===============================================================
        switch (verification) {
            case 1 -> sakilaTests.verifySchema(currentSchemaName);

            case 2 -> sakilaTests.verifyMySqlSyntax(currentSchemaName);

            case 3 -> sakilaTests.verifyConsumingLargeRecordsWithCursor(currentSchemaName);

            case 4 -> sakilaTests.verifyActiveRecords(currentSchemaName);

            case 5 -> sakilaTests.verifyUnions(currentSchemaName);

            case 6 -> sakilaTests.verifyWithInPredicates(currentSchemaName);

            case 7 -> sakilaTests.verifyStandardisationLimit(currentSchemaName);

            case 8 -> sakilaTests.verifyJoin(currentSchemaName);

            case 9 -> sakilaTests.verifyAliasing(currentSchemaName);

            case 10 -> sakilaTests.verifyImplicitJoins(currentSchemaName);

            case 11 -> sakilaTests.verifyNestedRecords(currentSchemaName);

            case 12 -> sakilaTests.testDynamicSql(currentSchemaName);

            case 13 -> sakilaTests.writeNestedRowsWithAdHocConverters(currentSchemaName);
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



