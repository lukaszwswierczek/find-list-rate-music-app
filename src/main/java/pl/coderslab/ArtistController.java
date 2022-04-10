package pl.coderslab;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.api.AudioDBApiService;
import pl.coderslab.model.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArtistController {

    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;
    private final AudioDBApiService audioDBApiService;

    public ArtistController(AlbumRepository albumRepository, TrackRepository trackRepository, AudioDBApiService audioDBApiService) {
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.audioDBApiService = audioDBApiService;
    }

    @GetMapping("/")
    public String searchArtistForm() {
        return "homepage";
    }

    @GetMapping("/albums/saved")
    public String displaySavedAlbums(Model model) {
        List<UserListAlbum> userAlbums = albumRepository.findAll();
        model.addAttribute("userAlbums", userAlbums);
        return "savedAlbums";
    }

    @GetMapping("/artist")
    @JsonProperty
    public String artistInfo(@RequestParam String artistName, Model model) {
        List<Artist> artistList = audioDBApiService.getArtistInfo(artistName);
        model.addAttribute("artistList", artistList);
        return "artistInfo";
    }

    @GetMapping("/albums")
    @JsonProperty
    public String getAlbums(@RequestParam String idArtist, Model model) {
        List<Album> albums = audioDBApiService.getAlbumsByArtist(idArtist);
        albums = albums.stream()
                .sorted(Comparator.comparing(Album::getIntYearReleased).reversed())
                .collect(Collectors.toList());
        String artistName = albums.stream()
                .map(Album::getStrArtist).findFirst().get().toString();
        model.addAttribute("artistName", artistName);
        model.addAttribute("albums", albums);
        return "albumList";
    }

    @GetMapping("/album/add")
    public String addUserAlbum(@RequestParam String idArtist, @RequestParam String idAlbum) {
        List<Album> albums = audioDBApiService.getSpecificAlbum(idAlbum);
        UserListAlbum userListAlbum = new UserListAlbum();
        albums.stream().forEach(data -> {
            userListAlbum.setArtistName(data.getStrArtist());
            userListAlbum.setAlbumTitle(data.getStrAlbum());
            userListAlbum.setGenre(data.getStrGenre());
            userListAlbum.setYearOfRelease(data.getIntYearReleased());
            userListAlbum.setRating("0");
            userListAlbum.setIdAlbum(idAlbum);
        });
        albumRepository.save(userListAlbum);
        return "redirect:/albums?idArtist=" + idArtist;
    }

    @GetMapping("/tracks")
    @JsonProperty
    public String getTracks(@RequestParam String idAlbum, Model model) {
        List<Track> tracks = audioDBApiService.getTrackList(idAlbum);
        model.addAttribute("tracks", tracks);
        return "trackList";
    }

    @GetMapping("/add/track-to-favorites")
    public String addTrackToFavorites(@RequestParam String idTrack, @RequestParam String idAlbum) {
        List<Track> tracks = audioDBApiService.getSpecificTrack(idTrack);
        UserAlbumTrack track = new UserAlbumTrack();
        tracks.stream().forEach(data -> {
            track.setAlbum(data.getStrAlbum());
            track.setArtist(data.getStrArtist());
            if(data.getStrGenre().isBlank()){
                track.setGenre("No data");
            } else {
                track.setGenre(data.getStrGenre());
            }
            track.setTitle(data.getStrTrack());
            track.setDuration(Integer.parseInt(data.getIntDuration()));
            track.setRating("0");
        });
        trackRepository.save(track);
        return "redirect:/tracks?idAlbum=" + idAlbum;
    }


//    @RequestMapping("/artist/get/{id}")
//    public Artist getArtistById(@PathVariable Long id) {
//        return artistRepository.findById(id).get();
//    }
//
//    @RequestMapping(value = "/artist/delete/{id}", method = RequestMethod.DELETE)
//    public String deleteArtist(@PathVariable Long id) {
//        artistRepository.deleteById(id);
//        return "redirect:artist/list";
//    }

}
