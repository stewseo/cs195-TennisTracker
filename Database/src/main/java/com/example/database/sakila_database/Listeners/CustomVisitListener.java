package com.example.database.sakila_database.Listeners;

import org.jooq.*;
import org.jooq.impl.DefaultVisitListener;
import org.jooq.tools.JooqLogger;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.jooq.impl.DSL.*;
import static org.jooq.tools.StringUtils.abbreviate;

public class CustomVisitListener extends DefaultVisitListener{

    private QueryPart queryPart;
    protected static final JooqLogger log = JooqLogger.getLogger(CustomVisitListener.class);
    private boolean anyAbbreviations = false;
    private boolean anyTables = false;
    private boolean anyFields = false;

    private boolean selectSteps = false;
    final Predicate<VisitContext> matcher;
    int hits;
    int maxLength = 100;

    public CustomVisitListener(Predicate<VisitContext> matcher) {
        this.matcher = matcher;
    }
    public CustomVisitListener(){
        matcher = null;
    }

    @Override
    public void visitStart(VisitContext context) {
        
        if (context.renderContext() != null) {
            QueryPart part = context.queryPart();
            QueryPart[] partsArray  = context.queryParts();

            if(part instanceof Table<?> table) {
                anyTables = true;
            }
            if(part instanceof Field<?> field) {
                anyFields = true;
            }

            if (part instanceof Param<?> param) {
                Object value = param.getValue();

                if (value instanceof String && ((String) value).length() > maxLength) {
                    anyAbbreviations = true;
                    context.queryPart(val(abbreviate((String) value, maxLength)));
                }
                else if (value instanceof byte[] && ((byte[]) value).length > maxLength) {
                    anyAbbreviations = true;
                    context.queryPart(val(Arrays.copyOf((byte[]) value, maxLength)));
                }
            }
        }
    }
    static int countTables = 0;
    static int countFields = 0;
    @Override
    public void visitEnd(VisitContext context) {

        if (anyAbbreviations) {
            log.info("anyAbbreviations true");
            if (context.queryPartsLength() == 1) {
                log.info("queryPartsLength == 1");
                context.renderContext().sql(" -- Bind values abbreviated");
            }
        }
        if (anyTables) {
        }
        if (anyFields) {
        }

    }
}
