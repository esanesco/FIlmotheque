package fr.eni.controller;

import fr.eni.bo.Movie;
import fr.eni.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller("movieBean")
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
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
        Movie m = movieService.getMovieById(id);
        model.addAttribute("movie", m);
        return "movie-detail";
    }
}
