package pl.lukaszswierczek.findListRateApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lukaszswierczek.findListRateApp.model.Rating;
import pl.lukaszswierczek.findListRateApp.model.UserTrack;
import pl.lukaszswierczek.findListRateApp.repository.RatingRepository;
import pl.lukaszswierczek.findListRateApp.service.UserTrackService;
import pl.lukaszswierczek.findListRateApp.user.CurrentUser;
import pl.lukaszswierczek.findListRateApp.user.User;
import pl.lukaszswierczek.findListRateApp.repository.UserTrackRepository;
import pl.lukaszswierczek.findListRateApp.service.ApiService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserTrackController {

    private final UserTrackRepository userTrackRepository;
    private final RatingRepository ratingRepository;
    private final UserTrackService userTrackService;

    public UserTrackController(UserTrackRepository userTrackRepository, RatingRepository ratingRepository, UserTrackService userTrackService) {
        this.userTrackRepository = userTrackRepository;
        this.ratingRepository = ratingRepository;
        this.userTrackService = userTrackService;
    }

    //track -- add
    @GetMapping("/add/track-to-favorites")
    public String addTrackToFavorites(@RequestParam String idTrack,
                                      @RequestParam String idAlbum,
                                      @AuthenticationPrincipal CurrentUser customUser) {
        //current user = listener
        User listener = customUser.getUser();
        userTrackService.executeNewUserTrack(listener, idTrack);
        return "redirect:/tracks?idAlbum=" + idAlbum;
    }

    //track -- list
    @GetMapping("/tracks/saved")
    public String displaySavedTracks(Model model,
                                     @AuthenticationPrincipal CurrentUser customUser) {

        User listener = customUser.getUser();
        List<UserTrack> listenerTracks = userTrackRepository.findByUser(listener);
        long total = listenerTracks.size();
        model.addAttribute("total", total);
        model.addAttribute("updateRating", new Rating());
        model.addAttribute("userTracks", listenerTracks);

        return "savedTracks";
    }

    @PostMapping("/tracks/edit-rating")
    public String updateRating(Rating rating,
                               Model model,
                               @AuthenticationPrincipal CurrentUser customUser) {

        model.addAttribute("updateRating", rating);
        Rating update = ratingRepository.findRatingByUserAndIdTrack(customUser.getUser(), rating.getIdTrack());
        update.setRating(rating.getRating());
        ratingRepository.save(update);
        return "redirect:/user/tracks/saved";
    }

    //track -- delete
    @GetMapping("/track/delete")
    public String deleteTrack(@RequestParam Long id) {
        UserTrack track = userTrackRepository.findById(id).get();
        userTrackRepository.delete(track);
        return "redirect:/user/tracks/saved";
    }


}
