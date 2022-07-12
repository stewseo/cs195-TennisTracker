package com.example.database.sakila_database.Listeners;

import org.jooq.DiagnosticsContext;
import org.jooq.impl.DefaultDiagnosticsListener;

import java.sql.SQLException;


public class DiagListener extends DefaultDiagnosticsListener {

    public DiagListener() throws SQLException {
    }

    @Override
    public void tooManyColumnsFetched(DiagnosticsContext ctx) {}

    @Override
    public void unnecessaryWasNullCall(DiagnosticsContext ctx) {}

    @Override
    public void missingWasNullCall(DiagnosticsContext ctx) {

    }

    @Override
    public void duplicateStatements(DiagnosticsContext ctx) {

    }

    @Override
    public void repeatedStatements(DiagnosticsContext ctx) {

    }


    @Override
    public void tooManyRowsFetched(DiagnosticsContext ctx) {

        System.out.println("Actual statement: " + ctx.actualStatement());

        System.out.println("Normalised statement: " + ctx.normalisedStatement());

        System.out.println("Repeated statements: " + ctx.repeatedStatements());
        }

}

