package fr.eni.mmi.controller;

import fr.eni.bo.Genre;
import fr.eni.bo.Movie;
import fr.eni.bo.Opinion;
import fr.eni.bo.Participant;
import fr.eni.exception.BusinessException;
import fr.eni.service.MovieService;
import fr.eni.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller("movieBean")
@RequestMapping("/movies")
@SessionAttributes({"userSession", "movieParticipants", "movieGenres"})
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String showAllMovies(Model model) {
        List<Movie> lstMovies = movieService.getAllMovies();
        if (lstMovies == null) {
            lstMovies = new ArrayList<>();
        }
        model.addAttribute("movies", lstMovies);
        return "view-movies";
    }

    @GetMapping("/detail")
    public String findMovie(@RequestParam(name = "id") long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        List<Opinion> opinionList = movieService.getOpinionByMovie(id);
        movie.setOpinions(opinionList);
        model.addAttribute("movie", movie);
        return "movie-detail";
    }

    // Création d'un nouveau film
    @GetMapping("/create")
    public String createMovie(Model model) {
        // Test si un membre est connecté
        Object user = model.getAttribute("userSession");
        if (user != null) {
            Movie movie = new Movie();
            // Ajout de l'instance dans le modèle
            model.addAttribute("movie", movie);
            return "add-new-movie";
        } else {
            return "redirect:/login";
        }
    }

    // Création d'un nouveau film
    @PostMapping("/create")
    public String createMovie(@Valid @ModelAttribute("movie") Movie movie,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes ra) {
        // Test si un membre est connecté
        Object user = model.getAttribute("userSession");
        if (user != null) {
            if (bindingResult.hasErrors()){
                return "add-new-movie";
            } else {
                System.out.println(movie);
                try {
                    movieService.saveMovie(movie);
                    ra.addFlashAttribute("messageSucces", "movie "+movie.getTitle()+" was created!!");
                    return "redirect:/movies";
                } catch (BusinessException e) {
                    System.err.println(e.getErrors());
                    model.addAttribute("movie", movie);
                    model.addAttribute("be", e.getErrors());
                    return "add-new-movie";
                }
            }

        } else {
            return "redirect:/login";
        }
    }

    // Injection en session des listes représentant les participants et les genres
    @ModelAttribute("movieParticipants")
    public List<Participant> getAllParticipants() {
        return movieService.getParticipants();
    }

    @ModelAttribute("movieGenres")
    public List<Genre> getAllGenres() {
        return movieService.getGenres();
    }


}
