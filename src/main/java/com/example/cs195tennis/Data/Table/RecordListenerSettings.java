package com.example.cs195tennis.Data.Table;

import org.jooq.RecordContext;
import org.jooq.impl.DefaultRecordListener;

import static com.example.cs195tennis.Util.Tools.print;

public class RecordListenerSettings extends DefaultRecordListener {
    @Override
    public void storeStart(RecordContext ctx) {
        print("storeStart " + ctx);
    }

    @Override
    public void storeEnd(RecordContext ctx) {
        print("storeEnd " + ctx);
    }

    @Override
    public void exception(RecordContext ctx) {
        print("RecordContext Exception: " + ctx.exception());
        print("RecordContext recordType: " + ctx.recordType());
        print("RecordContext recordType: " + ctx.record());
    }
}
