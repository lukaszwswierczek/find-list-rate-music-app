package pl.lukaszswierczek.findListRateApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lukaszswierczek.findListRateApp.model.Rating;
import pl.lukaszswierczek.findListRateApp.model.UserTrack;
import pl.lukaszswierczek.findListRateApp.repository.RatingRepository;
import pl.lukaszswierczek.findListRateApp.user.CurrentUser;
import pl.lukaszswierczek.findListRateApp.user.User;
import pl.lukaszswierczek.findListRateApp.repository.UserTrackRepository;
import pl.lukaszswierczek.findListRateApp.service.ApiService;
import pl.lukaszswierczek.findListRateApp.model.Track;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserTrackController {

    private final ApiService apiService;
    private final UserTrackRepository userTrackRepository;
    private final RatingRepository ratingRepository;

    public UserTrackController(ApiService apiService, UserTrackRepository userTrackRepository, RatingRepository ratingRepository) {
        this.apiService = apiService;
        this.userTrackRepository = userTrackRepository;
        this.ratingRepository = ratingRepository;
    }

    //track -- add
    @GetMapping("/add/track-to-favorites")
    public String addTrackToFavorites(@RequestParam String idTrack,
                                      @RequestParam String idAlbum,
                                      @AuthenticationPrincipal CurrentUser customUser) {

        //current user = listener
        User listener = customUser.getUser();

        if (userTrackRepository.findUserTrackByUserAndIdTrack(listener, Long.parseLong(idTrack)) == null) {

            List<Track> tracks = apiService.getSpecificTrack(idTrack);
            UserTrack track = new UserTrack();
            tracks.stream().forEach(data -> {
                track.setIdTrack(Long.parseLong(data.getIdTrack()));
                track.setAlbum(data.getStrAlbum());
                track.setIdAlbum(Long.parseLong(data.getIdAlbum()));
                track.setArtist(data.getStrArtist());
                track.setGenre(data.getStrGenre());
                track.setTitle(data.getStrTrack());
                //formatting duration
                String duration = data.getIntDuration().substring(0, 3);
                track.setDuration(String.format("%02d:%02d", Integer.parseInt(duration) / 60, Integer.parseInt(duration) % 60));
                track.setUser(listener);

            });

            //rating creation
            Rating rating = new Rating();
            rating.setIdTrack(track.getIdTrack());
            rating.setRating("0");
            rating.setUser(listener);
            ratingRepository.save(rating);
            track.setRating(rating);

            //saving track
            userTrackRepository.save(track);
        }
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
