package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.UserAlbumRepository;
import pl.coderslab.api.ApiService;
import pl.coderslab.model.*;

import java.util.*;


@Controller
public class UserAlbumController {

    private final UserAlbumRepository userAlbumRepository;
    private final ApiService apiService;

    public UserAlbumController(UserAlbumRepository userAlbumRepository, ApiService apiService) {
        this.userAlbumRepository = userAlbumRepository;
        this.apiService = apiService;
    }


    //album -- add
    @GetMapping("/album/add")
    public String addUserAlbum(@RequestParam String idArtist, @RequestParam String idAlbum) {
        List<Album> albums = apiService.getSpecificAlbum(idAlbum);
        UserAlbum userAlbum = new UserAlbum();
        albums.stream().forEach(data -> {
            userAlbum.setArtist(data.getStrArtist());
            userAlbum.setAlbum(data.getStrAlbum());
            userAlbum.setGenre(data.getStrGenre());
            userAlbum.setYearOfRelease(data.getIntYearReleased());
            userAlbum.setRating("0");
            userAlbum.setIdAlbum(Long.valueOf(idAlbum));
        });
        userAlbumRepository.save(userAlbum);
        return "redirect:/albums?idArtist=" + idArtist;
    }

    //album -- list
    @GetMapping("/albums/saved")
    public String displaySavedAlbums(Model model) {
        List<UserAlbum> userAlbums = userAlbumRepository.findAll();
        model.addAttribute("userAlbums", userAlbums);
        return "savedAlbums";
    }


    //album -- delete
    @GetMapping("/album/delete")
    public String deleteAlbum(@RequestParam String idAlbum) {
        long id = Long.parseLong(idAlbum);
        Optional<UserAlbum> album = userAlbumRepository.findById(id);
        userAlbumRepository.delete(album.get());
        return "redirect:/albums/saved";
    }



}
