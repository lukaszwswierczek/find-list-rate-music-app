package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    public UserTrackController(ApiService apiService, UserTrackRepository userTrackRepository) {
        this.apiService = apiService;
        this.userTrackRepository = userTrackRepository;
    }

    //track -- add
    @GetMapping("/add/track-to-favorites")
    public String addTrackToFavorites(@RequestParam String idTrack, @RequestParam String idAlbum) {
        List<Track> tracks = apiService.getSpecificTrack(idTrack);
        UserTrack track = new UserTrack();
        tracks.stream().forEach(data -> {
            track.setIdTrack(data.getIdTrack());
            track.setAlbum(data.getStrAlbum());
            track.setIdAlbum(data.getIdAlbum());
            track.setArtist(data.getStrArtist());
            track.setGenre(data.getStrGenre());
            track.setTitle(data.getStrTrack());
            //zamiana sekund na 0m:ss
            String duration = data.getIntDuration().substring(0, 3);
            track.setDuration(String.format("%02d:%02d", Integer.parseInt(duration) / 60, Integer.parseInt(duration) % 60));
            track.setRating("0");
        });
        userTrackRepository.save(track);
        return "redirect:/tracks?idAlbum=" + idAlbum;
    }

    //track -- list
    @GetMapping("/tracks/saved")
    public String displaySavedTracks(Model model) {
        List<UserTrack> userTracks = userTrackRepository.findAll();
        model.addAttribute("userTracks", userTracks);
        return "savedTracks";
    }

    //track -- delete
    @GetMapping("/track/delete")
    public String deleteTrack(@RequestParam Long idTrack) {
        Optional<UserTrack> track = userTrackRepository.findById(idTrack);
        userTrackRepository.delete(track.get());
        return "redirect:/tracks/saved";
    }

}
