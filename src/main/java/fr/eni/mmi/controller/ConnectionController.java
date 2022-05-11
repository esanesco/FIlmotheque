package fr.eni.mmi.controller;

import fr.eni.bo.Member;
import fr.eni.service.ConnectionService;
import fr.eni.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"userSession"})
public class ConnectionController {

    private ConnectionService connectionService;

    @Autowired
    public ConnectionController(@Qualifier("connectionServiceImpl") ConnectionService connectionService){
        this.connectionService = connectionService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true) String password,
                        Model model,
                        RedirectAttributes ra) {
        Member user = connectionService.login(email, password);
        System.out.println(user);
        if (user != null)
        {
            model.addAttribute("userSession", user);
            ra.addFlashAttribute("messageSucces", "you were successfully connected");
            return "redirect:/movies";

        } else {
            ra.addFlashAttribute("messageError", "incorrect user or password");
            return "login";
        }

    }

    @GetMapping("/logout")
    public String disconnect(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }

}
