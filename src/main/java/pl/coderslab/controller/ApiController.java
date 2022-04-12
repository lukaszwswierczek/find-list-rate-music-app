package pl.coderslab.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.api.ApiService;
import pl.coderslab.model.Album;
import pl.coderslab.model.Artist;
import pl.coderslab.model.Track;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    //obsługa API -- artist; widok na artistInfo
    @GetMapping("/artist")
    @JsonProperty
    public String artistInfo(@RequestParam String artistName, Model model) {
        List<Artist> artistList = apiService.getArtistInfo(artistName);
        model.addAttribute("artistList", artistList);
        return "artistInfo";
    }

    //obsługa API -- album; widok na albumList
    @GetMapping("/albums")
    @JsonProperty
    public String getAlbums(@RequestParam String idArtist, Model model) {
        List<Album> albums = apiService.getAlbumsByArtist(idArtist);
        //sorting stream by year
        albums = albums.stream()
                .sorted(Comparator.comparing(Album::getIntYearReleased).reversed())
                .collect(Collectors.toList());
        String artistName = albums.stream()
                .map(Album::getStrArtist).findFirst().get().toString();
        model.addAttribute("artistName", artistName);
        model.addAttribute("albums", albums);
        return "albumList";
    }

    //obsługa API -- track; widok na trackList
    @GetMapping("/tracks")
    @JsonProperty
    public String getTracks(@RequestParam String idAlbum, Model model) {
        List<Track> tracks = apiService.getTrackList(idAlbum);
        model.addAttribute("tracks", tracks);
        return "trackList";
    }
}
