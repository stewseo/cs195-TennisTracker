package com.example.database.sakila_database.TestDataManipulation;

import com.example.database.connection.Database;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.LoggerListener;

import java.sql.Connection;

import static org.jooq.impl.DSL.constraint;

public class AlterTable extends Database {



    public static void addPrimaryKey(Table<?> table, Name constraintName, Name primaryKeyName) {
        ConstraintTypeStep constraintTypeStep =
                constraint(constraintName);

        ConstraintEnforcementStep primaryKey =
                constraint()
                        .primaryKey(primaryKeyName);


       ctx
                .alterTable(table)
                .add(constraint(constraintName) //Constraint
                        .primaryKey(primaryKeyName) //Constraint Type Step
                )
                .execute();

    }

    public static void dropConstraint(Table<?> table, Name constraint) {
        ConstraintTypeStep constraintTypeStep =
                constraint(constraint);
        ctx
                .alterTable(table)
                .dropConstraint(constraintTypeStep)
                .execute();

    }

    public static void createFieldIndex(String constraint, String referenceTableName, String referenceCol) {
        ctx
                .createIndex(constraint)
                .on(referenceTableName, referenceCol)
                .execute();

    }

    public static void createFKIndex(String foriegnKeyConstraint, String referenceTableName, String referenceCol) {
        ctx
                .createIndex(foriegnKeyConstraint)
                .on(referenceTableName, referenceCol)
                .execute();

    }
}
