-- Retrieve a list of all the games, ordered by date with the most recent game coming first.
-- SELECT * 
  -- FROM game 
  -- ORDER BY time DESC
  -- ;

-- Retrieve all the games that occurred in the past week.
-- SELECT * 
  -- FROM game 
  -- WHERE time > '2018-10-14 00:00:00'
  -- ;

-- Retrieve a list of players who have (non-NULL) names.
-- SELECT * 
  -- FROM player 
  -- WHERE name is not NULL
  -- ;

-- Retrieve a list of IDs for players who have some game score larger than 2000.
-- SELECT playerID 
  -- FROM playergame 
  -- WHERE score > 2000
  -- ;

-- Retrieve a list of players who have GMail accounts.
-- SELECT * 
  -- FROM player 
  -- WHERE RIGHT (emailAddress, 9) = 'gmail.edu'
  -- ;

-- Retrieve all “The King”’s game scores in decreasing order.
-- SELECT * 
  -- FROM player p, playergame pg 
  -- WHERE pg.playerID=p.ID 
  -- AND p.name='The King' 
  -- ORDER BY pg.score DESC
  -- ;

-- Retrieve the name of the winner of the game played on 2006-06-28 13:20:00.
-- SELECT p.name 
  --FROM game g, player p, playergame pg 
  -- WHERE p.ID=pg.playerID 
  -- AND g.ID=pg.gameID 
  -- AND time = '2006-06-28 13:20:00'
  -- ;

-- So what does that P1.ID < P2.ID clause do in the last example query?
-- It makes sure that Player One's ID is less than Player Two's ID when they have the same name

-- The query that joined the Player table to itself seems rather contrived. Can you think of a realistic situation in which you’d want to join a table to itself?
-- You may want to join a table to itself if you want to compare different records of the table to each other. For instance, the example query joins the Player table
-- to itself to see if any of the Players have the same name but different identity. 

