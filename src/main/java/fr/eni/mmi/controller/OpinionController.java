package fr.eni.mmi.controller;

import fr.eni.bo.Member;
import fr.eni.bo.Movie;
import fr.eni.bo.Opinion;
import fr.eni.exception.BusinessException;
import fr.eni.service.MovieService;
import fr.eni.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/opinions")
@SessionAttributes({"userSession", "selectedMovie"})
public class OpinionController {

    private MovieService movieService;

    @Autowired
    public OpinionController(MovieServiceImpl movieService){
        this.movieService = movieService;
    }

    @GetMapping("/add")
    public String createOpinion(@RequestParam("id")long idMovie, Model model, RedirectAttributes ra)
    {
        //Test si un membre est connecté
        Object membre = model.getAttribute("userSession");
        if (membre!=null)
        {
            model.addAttribute("selectedMovie", movieService.getMovieById(idMovie));
            Opinion opinion = new Opinion();
            model.addAttribute("opinion", opinion);
            return "movie-new-opinion";

        }else {
            ra.addFlashAttribute("messageError", "you must login to add an opinion");
            return "redirect:/login";
        }
    }

    @PostMapping("/add")
    public String createOpinion(@ModelAttribute("opinion") Opinion opinion, Model model, RedirectAttributes ra) {
        //Test si un membre est connecté
        Object member = model.getAttribute("userSession");
        if (member!=null)
        {
            try {
                opinion.setMember((Member) member);
                System.out.println(opinion);

                Movie selectedMovie = (Movie) model.getAttribute("selectedMovie");

                movieService.saveOpinion(opinion, selectedMovie);
                ra.addFlashAttribute("messageSucces", "opinion was successfully added");

                return "redirect:/movies";

            }catch (BusinessException e){
                ra.addFlashAttribute("messageError", e.getErrors());
                return "movie-new-opinion";
            }

        }else {
            ra.addFlashAttribute("messageError", "you must login to add an opinion");
            return "redirect:/login";
        }
    }
}
