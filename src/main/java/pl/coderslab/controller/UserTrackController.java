package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Rating;
import pl.coderslab.repository.RatingRepository;
import pl.coderslab.repository.UserTrackRepository;
import pl.coderslab.api.ApiService;
import pl.coderslab.model.Track;
import pl.coderslab.model.UserTrack;

import java.util.List;
import java.util.Optional;

@Controller
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
    public String addTrackToFavorites(@RequestParam String idTrack, @RequestParam String idAlbum) {
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
        });
        //new rating for a track
        Rating rating = new Rating();
        rating.setIdTrack(track.getIdTrack());
        rating.setRating("0");
        ratingRepository.save(rating);

        track.setRating(ratingRepository.getById(track.getIdTrack()));
        userTrackRepository.save(track);
        return "redirect:/tracks?idAlbum=" + idAlbum;
    }

    //track -- list
    @GetMapping("/tracks/saved")
    public String displaySavedTracks(Model model) {
        List<UserTrack> userTracks = userTrackRepository.findAll();
        List<Rating> ratings = ratingRepository.findAll();
        long total = userTracks.size();
        model.addAttribute("total", total);
        model.addAttribute("updateRating", new Rating());
        model.addAttribute("userTracks", userTracks);
        model.addAttribute("ratings", ratings);
        return "savedTracks";
    }

    @PostMapping("/tracks/saved")
    public String updateRating(Rating rating, Model model) {
        model.addAttribute("updateRating",rating);
        Rating trackRating = ratingRepository.findByIdTrack(rating.getIdTrack());
        trackRating.setRating(rating.getRating());
        ratingRepository.save(rating);
        return "redirect:/tracks/saved";
    }

    //track -- delete
    @GetMapping("/track/delete")
    public String deleteTrack(@RequestParam Long idTrack) {
        Optional<UserTrack> track = userTrackRepository.findById(idTrack);
        userTrackRepository.delete(track.get());
        return "redirect:/tracks/saved";
    }


}
