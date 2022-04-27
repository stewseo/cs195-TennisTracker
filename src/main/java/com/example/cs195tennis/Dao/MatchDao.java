package com.example.cs195tennis.Dao;


import com.example.cs195tennis.model.Match;

import java.util.List;

public class MatchDao {

    List<Match> listMatches;

    MatchDao(){}

    private final String CREATE_TABLE_MATCH_ANALYSIS_OVERVIEW = "match_id,player, set, serve_pts,a ces,c,first_in,first_won,second_in,second_won,bk_pts,bp_saved,return_pts,return_pts_won, winners, winners_fh, winners_bh, unforced, unforced_fh, unforced_bh";

    private final String CREATE_TABLE_SHOT_TYPE = "shot_type";

    private final String CREATE_TABLE_SHOT_DIRECTION = "shot_direction";

    private final String CREATE_ = "match_id,row,pts,pts_won,first_in, aces, svc_winners,rally_winners, rally_forced, unforced, dfs";

    private final String CREATE_TABLE_STAT_OVERVIEW = "Stat Overview";


    private static final String CREATE_TABLE_KEY_POINT_OUTCOME = "CREATE TABLE KEY_POINT_OUTCOME"
            + "("
            + " ID match_id,"
            + " player TEXT NOT NULL,"
            + " row TEXT NOT NULL,"
            + " shots TEXT NOT NULL,"
            + " pt_ending,winners,,"
            + " induced_forced TEXT,"
            + " height TEXT,"
            + " first_won TEXT,"
            + " second_in_pts_won "
            + " second_wonEXT ";

}