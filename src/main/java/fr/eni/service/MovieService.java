package fr.eni.service;

import fr.eni.bo.Genre;
import fr.eni.bo.Movie;
import fr.eni.bo.Opinion;
import fr.eni.bo.Participant;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(long id);

    List<Genre> getGenres();

    List<Participant> getParticipants();

    Genre getGenreById(long id);

    Participant getParticipantById(long id);

    void saveMovie(Movie movie);

    void saveOpinion(Opinion opinion, Movie selectedMovie);

    List<Opinion> getOpinionByMovie(long idMovie);
}
