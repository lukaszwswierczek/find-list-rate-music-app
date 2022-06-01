package pl.lukaszswierczek.findListRateApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.lukaszswierczek.findListRateApp.model.UserAlbum;
import pl.lukaszswierczek.findListRateApp.repository.UserAlbumRepository;
import pl.lukaszswierczek.findListRateApp.user.CurrentUser;

import java.util.Collections;
import java.util.List;

@Controller
public class HomepageController {

    private final UserAlbumRepository userAlbumRepository;

    public HomepageController(UserAlbumRepository userAlbumRepository) {
        this.userAlbumRepository = userAlbumRepository;
    }

    //homepage
    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }


    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model) {

        if (userAlbumRepository.findByUser(customUser.getUser()) != null) {
            List<UserAlbum> userAlbums = userAlbumRepository.findByUser(customUser.getUser());
            Collections.reverse(userAlbums);
            model.addAttribute("userAlbums", userAlbums);
        }
        return "homepage";
    }
}
