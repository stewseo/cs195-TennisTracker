//package com.example.database.Alter;
//
//import org.jooq.ConstraintEnforcementStep;
//import org.jooq.ConstraintTypeStep;
//import org.jooq.Name;
//import org.jooq.Table;
//
//import static org.jooq.impl.DSL.constraint;
//
//public class ModifyTable {
//
//    public static void addPrimaryKey(Table<?> table, Name constraintName, Name primaryKeyName) {
//        ConstraintTypeStep constraintTypeStep =
//                constraint(constraintName);
//
//        ConstraintEnforcementStep primaryKey =
//                constraint()
//                        .primaryKey(primaryKeyName);
//
//
//                ctx()
//                        .alterTable(table)
//                        .add(constraint(constraintName) //Constraint
//                                .primaryKey(primaryKeyName) //Constraint Type Step
//                        )
//                        .execute();
//
//    }
//
//    public static void dropConstraint(Table<?> table, Name constraint) {
//        Connect connect = new Connect();
//        ConstraintTypeStep constraintTypeStep =
//                constraint(constraint);
//
//        ctx()
//                .alterTable(table)
//                .dropConstraint(constraintTypeStep)
//                .execute();
//
//    }
//
//    public static void createFieldIndex(String constraint, String referenceTableName, String referenceCol) {
//        ctx()
//                .createIndex(constraint)
//                .on(referenceTableName, referenceCol)
//                .execute();
//
//    }
//
//    public static void createFKIndex(String foriegnKeyConstraint, String referenceTableName, String referenceCol) {
//        ctx()
//                .createIndex(foriegnKeyConstraint)
//                .on(referenceTableName, referenceCol)
//                .execute();
//
//    }
//}
