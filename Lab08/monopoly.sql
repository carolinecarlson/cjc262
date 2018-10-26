--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden extended by Dana Drosdick and Caroline Carlson
-- @version Fall, 2018
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS GameState;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;

-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	score integer
	);

CREATE TABLE GameState (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	cashAmt money,
	numHouses integer,
	numHotels integer,
	currentLocation varchar,
	properties varchar
	);


-- GameState(gameID (primary),
-- playerID (primary),cashAmt,numHouses,numHotels,peiceLocation,uCards)

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON GameState TO PUBLIC;


-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2018-10-16 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00);
INSERT INTO PlayerGame VALUES (1, 2, 0.00);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00);
INSERT INTO PlayerGame VALUES (2, 2, 0.00);
INSERT INTO PlayerGame VALUES (2, 3, 500.00);
INSERT INTO PlayerGame VALUES (3, 2, 0.00);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00);

INSERT INTO GameState VALUES (2, 1, 5500.00,2,3,'Broadway','Broadway,Virgina,Chicago Street');
INSERT INTO GameState VALUES (2, 2, 5511.00,2,3,'Broadway','Broadway,Virgina,Chicago Street');
INSERT INTO GameState VALUES (2, 3, 5512.00,2,3,'Broadway','Broadway,Virgina,Chicago Street');

--Retrieve a list of all the games, ordered by date with the most recent game coming first.
SELECT * FROM game ORDER BY time DESC;

--Retrieve all the games that occurred in the past week.
SELECT * FROM game WHERE time > '2018-10-14 00:00:00';

--Retrieve a list of players who have (non-NULL) names.
SELECT * FROM player WHERE name is not NULL;

--Retrieve a list of IDs for players who have some game score larger than 2000.
SELECT playerID FROM playergame WHERE score > 2000;

--Retrieve a list of players who have GMail accounts.
SELECT * FROM player WHERE RIGHT (emailAddress, 9) = 'gmail.edu';

--Retrieve all “The King”’s game scores in decreasing order.
SELECT * FROM player p, playergame pg WHERE pg.playerID=p.ID AND p.name='The King' ORDER BY pg.score DESC;

--Retrieve the name of the winner of the game played on 2006-06-28 13:20:00.
SELECT p.name FROM game g, player p, playergame pg WHERE p.ID=pg.playerID AND g.ID=pg.gameID AND time = '2006-06-28 13:20:00';

--So what does that P1.ID < P2.ID clause do in the last example query?
--It makes sure that Player One's ID does not equal Player Two's ID

--The query that joined the Player table to itself seems rather contrived. Can you think of a realistic situation in which you’d want to join a table to itself?
--You may want to going a table to itself if you want to compare different records of the table to each other. For instance, the example query joins the Player table
--to itself to see if any of the Players have the same name but different identity.




