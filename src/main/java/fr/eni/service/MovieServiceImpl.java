package fr.eni.service;

import fr.eni.bo.*;
import fr.eni.dal.GenreRepository;
import fr.eni.dal.MovieRepository;
import fr.eni.dal.OpinionRepository;
import fr.eni.dal.ParticipantRepository;
import fr.eni.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    // injection des Repository
    private GenreRepository genreRepository;
    private MovieRepository movieRepository;
    private ParticipantRepository participantRepository;
    private OpinionRepository opinionRepository;

    @Autowired
    public MovieServiceImpl(GenreRepository genreRepository,
                            MovieRepository movieRepository,
                            ParticipantRepository participantRepository,
                            OpinionRepository opinionRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.participantRepository = participantRepository;
        this.opinionRepository = opinionRepository;

    }
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(long id) {
        return movieRepository.getById(id);
    }


    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Genre getGenreById(long id) {
        return genreRepository.getById(id);
    }

    @Override
    public Participant getParticipantById(long id) {
        return participantRepository.getById(id);
    }

    @Override
    public void saveMovie(Movie movie) {
        // Validation des données
        BusinessException be = new BusinessException();
        validateTitle(movie.getTitle(), be);
        validateGenre(movie.getGenre(), be);
        validateDirector(movie.getDirector(), be);
        validateYear(movie.getYear(), be);
        validateDuration(movie.getDuration(), be);
        validateSynopsis(movie.getSynopsis(), be);

        if (be.isError()) {
            throw be;
        }
        movieRepository.save(movie);
    }
    private void validateTitle(String data, BusinessException be) {
        if (data == null || data.isBlank()) {
            be.addError("Le titre est obligatoire");
        }
    }

    private void validateGenre(Genre data, BusinessException be) {
        if (data == null || data.getId() == 0) {
            be.addError("Le genre est obligatoire");
        }
    }

    private void validateDirector(Participant data, BusinessException be) {
        if (data == null || data.getId() == 0) {
            be.addError("Le réalisateur est obligatoire");
        }
    }

    private void validateYear(int data, BusinessException be) {
        if (data < 1900 || data > 2100) {
            be.addError("L'année n'est pas correcte");
        }
    }

    private void validateDuration(int data, BusinessException be) {
        if (data <= 0) {
            be.addError("La durée est positive");
        }
    }

    private void validateSynopsis(String data, BusinessException be) {
        if (data == null || data.isBlank() || (!data.isBlank() && (data.length() < 20 || data.length() > 250))) {
            be.addError("Le synopsis doit faire entre 20 et 250 caractères");
        }
    }

    @Override
    public void saveOpinion(Opinion opinion, Movie selectedMovie) {
        // Valider les contraintes avant la sauvegarde
        // La note comprise entre 0 et 5
        // Un commentaire (entre 1 et 250 caractères)
        // Un membre associé

        BusinessException be = new BusinessException();
        validateNote(opinion.getNote(), be);
        validateComments(opinion.getComment(), be);
        validateMember(opinion.getMember(), be);
        if (be.isError()) {
            throw be;
        }
        // Objet détaché en ORM
        Optional<Movie> op = movieRepository.findById(selectedMovie.getId());
        if(op.isPresent()) {
            Movie entity = op.get();
            entity.getOpinions().add(opinion);
            movieRepository.save(entity);
        }
    }

    private void validateNote(int data, BusinessException be) {
        if (data < 0 || data > 5) {
            be.addError("La note doit être entre 0 et 5");
        }
    }

    private void validateComments(String data, BusinessException be) {
        if (data.isBlank() || !data.isBlank() && (data.length() < 1 || data.length() > 250)) {
            be.addError("Le commentaire doit faire entre 1 et 250 caractères");
        }
    }

    private void validateMember(Member data, BusinessException be) {
        if (data == null || data.getId() == 0) {
            be.addError("Le membre est obligatoire");
        }
    }

    @Override
    public List<Opinion> getOpinionByMovie(long idMovie) {
        List<Opinion> lst = opinionRepository.findOpinionsByMovie(idMovie);
        if(lst == null) {
            return new ArrayList<>();
        }else {
            return lst;
        }
    }
}
