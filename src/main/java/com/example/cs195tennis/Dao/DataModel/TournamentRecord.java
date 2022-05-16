package com.example.cs195tennis.Dao.DataModel;

import org.jooq.impl.CustomRecord;

public class TournamentRecord extends CustomRecord<TournamentRecord> {

    protected TournamentRecord() {
        super(TournamentTable.TOURNAMENT1);
    }

}
