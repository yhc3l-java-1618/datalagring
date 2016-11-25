#SELECT * FROM Movie m, Genre g, MovieActor ma, Actor a
SELECT m.*, a.* FROM Movie m
		JOIN MovieActor ma ON ma.movieId = m.movieId
		JOIN Actor a ON a.actorId = ma.actorId
		JOIN Genre g ON g.genreId = m.genreId
WHERE m.movieId = 1 
#AND g.`genreId` = m.`genreId` 
#AND ma.`movieId` = m.`movieId`
#AND a.`actorId` = ma.`actorId`;



