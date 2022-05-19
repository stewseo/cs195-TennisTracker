package com.example.cs195tennis.Dao.DataModel;


import org.jooq.impl.CustomRecord;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT;

public class TournamentRecord extends CustomRecord<TournamentRecord> {

    protected TournamentRecord() {
        super(TOURNAMENT);
    }
}