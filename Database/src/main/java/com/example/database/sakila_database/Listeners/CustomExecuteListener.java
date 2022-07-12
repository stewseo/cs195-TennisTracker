package com.example.database.sakila_database.Listeners;

import org.jooq.DSLContext;
import org.jooq.ExecuteContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.tools.JooqLogger;

public class CustomExecuteListener extends DefaultExecuteListener {
    protected static final JooqLogger log = JooqLogger.getLogger(DefaultExecuteListener.class);

    @Override
    public void exception(ExecuteContext ctx) {
        if(ctx.exception() != null) {
            if (log.isTraceEnabled()) {
                log.trace("exception Trace", ctx.exception());
            }
            else if (log.isDebugEnabled()) {
                log.debug("exception Debug", ctx.exception());
            }
        }
    }

    @Override
    public void executeEnd(ExecuteContext ctx) {
        if (ctx.rows() >= 0) {
            if(log.isTraceEnabled()) {
                log.trace("executeEnd Trace exception", ctx.exception());
            }
            else if (log.isDebugEnabled()) {
                log.debug("executeEnd Affected row(s)", ctx.rows());
            }

        }
    }

    @Override
    public void warning(ExecuteContext ctx) {

        if(log.isTraceEnabled()) {
            log.trace("executeStart trace enabled result: ", ctx.result());
        }
        else if(log.isDebugEnabled()) {
            log.debug("executeStart debug enabled result: ", ctx.result());
        }
    }
    @Override
    public void executeStart(ExecuteContext ctx) {

        DSLContext create = DSL.using(ctx.dialect(),

                new Settings().withRenderFormatted(true)
        );

        if (ctx.query() != null) {
            if(log.isTraceEnabled()) {
                log.trace("executeStart trace enabled result: ", ctx.result());
            }
            else if(log.isDebugEnabled()) {
                log.debug("executeStart debug enabled result: ", ctx.result());
            }
        }
        else if (ctx.routine() != null) {
            if(log.isTraceEnabled()) {
                log.trace("executeStart trace enabled routine:" +  create.renderInlined(ctx.routine()));
            }
            else if(log.isDebugEnabled()) {
                log.debug("executeStart debug enabled routine:" +  create.renderInlined(ctx.routine()));
            }
        }
        else if(ctx.result() != null) {
            if(log.isTraceEnabled()) {
                log.trace("executeStart trace result: "+ ctx.result());
            }
            else if(log.isDebugEnabled()) {
                log.debug("executeStart debug result:" +  create.renderInlined(ctx.routine()));
            }
        }
    }
}

