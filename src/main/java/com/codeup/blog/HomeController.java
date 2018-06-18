package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/portfolio")
    public String portfolio() {
        return "portfolio";
    }

    @GetMapping("/roll-dice")
    public String showDiceLinks() {

        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rolldice(@PathVariable int guess, Model model) {

        int roll = (int) Math.ceil(Math.random() * 6);

        boolean match = roll == guess;

        model.addAttribute("match", match);
        model.addAttribute("roll", roll);
        model.addAttribute("guess", guess);
        return "roll-dice";
    }

}
