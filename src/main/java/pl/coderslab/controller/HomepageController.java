package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomepageController {

    //homepage
    @GetMapping("/")
    public String searchArtistForm() {
        return "homepage";
    }
}
