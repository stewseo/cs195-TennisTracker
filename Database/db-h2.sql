DROP TABLE IF EXISTS Tournament
;
DROP TABLE IF EXISTS "Match"
;
DROP TABLE IF EXISTS Player
;
DROP TABLE IF EXISTS Rank
;
DROP TABLE IF EXISTS Court
;

DROP SEQUENCE IF EXISTS s_player_id
;
DROP SEQUENCE IF EXISTS s_match_id
;
DROP SEQUENCE IF EXISTS s_tournament_id
;

CREATE SEQUENCE s_player_id START WITH 1
;
CREATE SEQUENCE s_match_id START WITH 1
;
CREATE SEQUENCE s_tournament_id START WITH 1
;


CREATE TABLE Tournament (
  id INT NOT NULL,
  tourney_name VARCHAR(400),
  tourney_date DATE,
  tourney_level INT NOT NULL,

  CONSTRAINT pk_t_tournament PRIMARY KEY (ID)
)
;

CREATE TABLE "Match" (
  id INT NOT NULL,
  set_number INT NOT NULL,
  game_number INT NOT NULL,
  point_winner INT NOT NULL,
  point_loser INT NOT NULL,
  tourney_id INT NOT NULL,

  rec_timestamp TIMESTAMP,

  CONSTRAINT pk_t_match PRIMARY KEY (id),
  CONSTRAINT fk_t_match_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournament(id)
)

CREATE TABLE Court (
    id INT NOT NULL,
    court_name VARCHAR(50),
    surface VARCHAR(50) NOT NULL,

    CONSTRAINT uk_t_court_id PRIMARY KEY(id)
    CONSTRAINT fk_t_court_id FOREIGN KEY (tournament_id) REFERENCES tournament(id)
)
;

CREATE TABLE Player (
  id INT NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE,
  dominant_hand VARCHAR(50),
  country VARCHAR(50),

  CONSTRAINT pk_t_player PRIMARY KEY(id)
  CONSTRAINT fk_t_player_match_tournament_id FOREIGN KEY (match_id) REFERENCES MatchModel(id)
)
;
CREATE TABLE PlayerRank (
  id INT NOT NULL,
  ranking_date DATE,
  rank INT NOT NULL,
  player INT NOT NULL,
  points INT NOT NULL,

  CONSTRAINT pk_rank PRIMARY KEY(ranking_date, player_id),
  CONSTRAINT fk_rank_player_id FOREIGN KEY (player_id)
                                REFERENCES player (id)
                                ON DELETE CASCADE,
  CONSTRAINT fk_rank_tournament_id    FOREIGN KEY (tournament_id)
                                REFERENCES tournament (id)
                                ON DELETE CASCADE
)
;

CREATE TABLE AtpPlayer (
  id INT NOT NULL,
  name_first VARCHAR(400) NOT NULL,
  name_last VARCHAR(400) NOT NULL,

  CONSTRAINT uk_t_AtpPlayer_id PRIMARY KEY(id)
  CONSTRAINT fk_t_match_tournament_id FOREIGN KEY (match_id) REFERENCES tournament(id)

)
;
CREATE TABLE AtpPlayerRank (
  id INT NOT NULL,
  tournament_id VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE,

  CONSTRAINT pk_t_match PRIMARY KEY (ID)
)
;


INSERT INTO tournament VALUES (next value for s_author_id, 'George', 'Orwell', '1903-06-25')
;
INSERT INTO tournament VALUES (next value for s_author_id, 'Paulo', 'Coelho', '1947-08-24')
;

INSERT INTO book VALUES (1, 1, '1984'        , 1948, null)
;
INSERT INTO book VALUES (2, 1, 'Animal Farm' , 1945, null)
;
INSERT INTO book VALUES (3, 2, 'O Alquimista', 1988, null)
;
INSERT INTO book VALUES (4, 2, 'Brida'       , 1990, null)
;

INSERT INTO book_store (name) VALUES
	('Amazon'),
	('Barnes and Noble'),
	('Payot')
;

INSERT INTO book_to_book_store VALUES
	('Amazon', 1, 10),
	('Amazon', 2, 10),
	('Amazon', 3, 10),
	('Barnes and Noble', 1, 1),
	('Barnes and Noble', 3, 2),
	('Payot', 3, 1)
;

DROP ALIAS IF EXISTS count_books
;

CREATE OR REPLACE ALIAS count_books AS $$
int countBooks(Connection c, int authorID) throws SQLException {
    try (PreparedStatement s = c.prepareStatement("SELECT COUNT(*) FROM book WHERE author_id = ?")) {
        s.setInt(1, authorID);

        try (ResultSet rs = s.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }
}
$$
;