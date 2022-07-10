//package com.example.database.sakila_database.schema;
//
//import com.example.database.sakila_database.SakilaModel.Record.*;
//import com.example.database.sakila_database.SakilaModel.Record.CountryRecord;
//import com.example.database.sakila_database.SakilaModel.Table.*;
//import org.jooq.ForeignKey;
//import org.jooq.TableField;
//import org.jooq.UniqueKey;
//import org.jooq.impl.DSL;
//import org.jooq.impl.Internal;
//
//
//@SuppressWarnings({"all", "unchecked", "rawtypes"})
//public class Keys {
//
//    public static final UniqueKey<ActorRecord> ACTOR_PKEY =
//            Internal.createUniqueKey(Actor.ACTOR,
//                    DSL.name("actor_pkey"), new TableField[] { Actor.ACTOR.ACTOR_ID }, true);
//    public static final UniqueKey<AddressRecord> ADDRESS_PKEY =
//            Internal.createUniqueKey(Address.ADDRESS,
//                    DSL.name("address_pkey"), new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
//    public static final UniqueKey<CityRecord> CITY_PKEY =
//            Internal.createUniqueKey(City.CITY,
//                    DSL.name("city_pkey"), new TableField[] { City.CITY.CITY_ID }, true);
//
//    public static final UniqueKey<CountryRecord> COUNTRY_PKEY =
//            Internal.createUniqueKey(Country.COUNTRY,
//                    DSL.name("country_pkey"), new TableField[] { Country.COUNTRY.COUNTRY_ID }, true);
//
//    public static final UniqueKey<CustomerRecord> CUSTOMER_PKEY =
//            Internal.createUniqueKey(Customer.CUSTOMER,
//                    DSL.name("customer_pkey"), new TableField[] { Customer.CUSTOMER.CUSTOMER_ID }, true);
//
//    public static final UniqueKey<FilmRecord> FILM_PKEY =
//            Internal.createUniqueKey(Film.FILM,
//                    DSL.name("film_pkey"), new TableField[] { Film.FILM.FILM_ID }, true);
//
//    public static final UniqueKey<FilmActorRecord> FILM_ACTOR_PKEY =
//            Internal.createUniqueKey(
//                    FilmActor.FILM_ACTOR, DSL.name("film_actor_pkey"),
//                    new TableField[] { FilmActor.FILM_ACTOR.ACTOR_ID, FilmActor.FILM_ACTOR.FILM_ID }, true);
//
//
//    public static final UniqueKey<CountryRecord> COUNTRY_KEY =
//            Internal.createUniqueKey(
//                    Country.COUNTRY,
//                    DSL.name("country_pkey"),
//                    new TableField[]{Country.COUNTRY.COUNTRY_ID},
//                    true
//            );
//
//    public static final UniqueKey<TournamentRecord> TOURNAMENT_PKEY =
//            Internal.createUniqueKey(
//                    Tournament.TOURNAMENT,
//                    DSL.name("tournament_pkey"),
//                    new TableField[]{Tournament.TOURNAMENT.TOURNAMENT_ID},
//                    true
//            );
//
//    public static final UniqueKey<MatchRecord> MATCH_PKEY =
//            Internal.createUniqueKey(
//                    Match.MATCH,
//                    DSL.name("match_pkey"),
//                    new TableField[]{ Match.MATCH.MATCH_ID },
//                    true
//            );
//
//    public static final UniqueKey<SetRecord> SET_PKEY =
//            Internal.createUniqueKey(
//                    Set.SET,
//                    DSL.name("set_pkey"),
//                    new TableField[]{Set.SET.SET_ID},
//                    true
//            );
//
//    public static final UniqueKey<GameRecord> GAME_PKEY =
//            Internal.createUniqueKey(
//                    Game.GAME,
//                    DSL.name("game_pkey"),
//                    new TableField[]{Game.GAME.GAME_ID},
//                    true);
//
//    public static final UniqueKey<WtaPlayerRecord> WTAPLAYER_PKEY =
//            Internal.createUniqueKey(
//            WtaPlayer.WTA_PLAYER,
//            DSL.name("wta_player_pkey"),
//            new TableField[]{WtaPlayer.WTA_PLAYER.PLAYER_ID},
//            true);
//
//    public static final UniqueKey<PlayerRecord> PLAYER_PKEY =
//            Internal.createUniqueKey(
//                    Player.PLAYER,
//                    DSL.name("player_pkey"),
//                    new TableField[]{Player.PLAYER.PLAYER_ID},
//                    true
//            );
//    public static final UniqueKey<AtpRankRecord> ATPRANK_PKEY =
//            Internal.createUniqueKey(AtpRank.ATP_RANK,
//                    DSL.name("atp_rank_pkey"),
//                    new TableField[]{AtpRank.ATP_RANK.ID},
//                    true
//            );
//    public static final UniqueKey<WtaRankRecord> WTARANK_PKEY =
//            Internal.createUniqueKey(
//                    WtaRank.WTA_RANK,
//                    DSL.name("wta_rank_pkey"),
//                    new TableField[]{WtaRank.WTA_RANK.ID},
//                    true
//            );
//
//    public static final UniqueKey<ServeStatsRecord> SERVE_STATS_RECORD =
//            Internal.createUniqueKey(ServeStats.SERVESTATS, "serve_stats_pkey",
//                    new TableField[]{ServeStats.SERVESTATS.SERVE_ID}, true);
//
//
//
//    // -------------------------------------------------------------------------
//    // FOREIGN KEY: Match_Match_Tournament_id = Tournament_Tournament_id
//    // -------------------------------------------------------------------------
//
//
//    public static final ForeignKey<CityRecord, CountryRecord> CITY__CITY_COUNTRY_ID_FKEY =
//            Internal.createForeignKey(City.CITY, DSL.name("city_country_id_fkey"),
//                    new TableField[] { City.CITY.COUNTRY_ID }, Keys.COUNTRY_PKEY,
//                    new TableField[] { Country.COUNTRY.COUNTRY_ID }, true);
//
//    public static final ForeignKey<FilmActorRecord, ActorRecord> FILM_ACTOR__FILM_ACTOR_ACTOR_ID_FKEY =
//            Internal.createForeignKey(FilmActor.FILM_ACTOR, DSL.name("film_actor_actor_id_fkey"),
//                    new TableField[] { FilmActor.FILM_ACTOR.ACTOR_ID },
//                    Keys.ACTOR_PKEY, new TableField[] { Actor.ACTOR.ACTOR_ID }, true);
//
//    public static final ForeignKey<FilmActorRecord, FilmRecord> FILM_ACTOR__FILM_ACTOR_FILM_ID_FKEY =
//            Internal.createForeignKey(FilmActor.FILM_ACTOR, DSL.name("film_actor_film_id_fkey"),
//                    new TableField[] { FilmActor.FILM_ACTOR.FILM_ID },
//                    Keys.FILM_PKEY, new TableField[] { Film.FILM.FILM_ID }, true);
//
//    public static final ForeignKey<CustomerRecord, AddressRecord> CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY =
//            Internal.createForeignKey(Customer.CUSTOMER, DSL.name("customer_address_id_fkey"),
//                    new TableField[] { Customer.CUSTOMER.ADDRESS_ID },
//                    Keys.ADDRESS_PKEY, new TableField[] { Address.ADDRESS.ADDRESS_ID }, true);
//
//    public static final ForeignKey<AddressRecord, CityRecord> ADDRESS__ADDRESS_CITY_ID_FKEY =
//            Internal.createForeignKey(Address.ADDRESS, DSL.name("address_city_id_fkey"),
//                    new TableField[] { Address.ADDRESS.CITY_ID },
//                    Keys.CITY_PKEY, new TableField[] {
//                            City.CITY.CITY_ID }, true);
//    public static final ForeignKey<MatchRecord, TournamentRecord> MATCH__MATCH_GRAND_SLAM_TOURNAMENT_ID_FKEY =
//            Internal.createForeignKey(
//                    Match.MATCH,
//                    DSL.name("match_grand_slam_tournament_id_fkey"),
//                    new TableField[]{Match.MATCH.TOURNAMENT_ID},
//                    Keys.TOURNAMENT_PKEY,
//                    new TableField[] { Tournament.TOURNAMENT.TOURNAMENT_ID},
//                    true
//            );
//
//    public static final ForeignKey<SetRecord, MatchRecord> SET__SET_MATCH_ID_FKEY =
//            Internal.createForeignKey(
//                    Set.SET,
//                    DSL.name("set_match_id_fkey"),
//                    new TableField[] { Set.SET.MATCH_ID },
//                    Keys.MATCH_PKEY,
//                    new TableField[] { Match.MATCH.MATCH_ID},
//                    true
//            );
//
//
//    public static final ForeignKey<GameRecord, SetRecord> GAME__GAME_SET_ID_FKEY =
//            Internal.createForeignKey(
//                    Game.GAME,
//                    DSL.name("game_set_id_fkey"),
//                    new TableField[]{Game.GAME.SET_ID},
//                    Keys.SET_PKEY,
//                    new TableField[] { Set.SET.SET_ID},
//                    true
//            );
//
//    public static final ForeignKey<MatchRecord, PlayerRecord> MATCH__MATCH_ATP_PLAYER_ID_FKEY =
//                Internal.createForeignKey(
//                        Match.MATCH,
//                        DSL.name("match_player_id_fkey"),
//                        new TableField[] { Match.MATCH.PLAYER_ID },
//                        Keys.PLAYER_PKEY,
//                        new TableField[] { Player.PLAYER.PLAYER_ID },
//                        true
//                );
//
//    public static final ForeignKey<AtpRankRecord, PlayerRecord> ATPRANK__ATP_RANK_PLAYER_ID_FKEY =
//            Internal.createForeignKey(
//                    AtpRank.ATP_RANK,
//                    DSL.name("atp_rank_atp_player_id_fkey"),
//                    new TableField[]{AtpRank.ATP_RANK.ID},
//                    Keys.PLAYER_PKEY,
//                    new TableField[] { Player.PLAYER.PLAYER_ID},
//                    true
//            );
//
//    public static final ForeignKey<WtaRankRecord, WtaPlayerRecord> WTARANK__ATP_RANK_PLAYER_ID_FKEY =
//            Internal.createForeignKey(
//                    WtaRank.WTA_RANK,
//                    DSL.name("wta_rank_wta_player_id_fkey"),
//                    new TableField[]{WtaRank.WTA_RANK.PLAYER_ID},
//                    Keys.WTAPLAYER_PKEY,
//                    new TableField[] { WtaPlayer.WTA_PLAYER.PLAYER_ID},
//                    true
//            );
//
//
//}