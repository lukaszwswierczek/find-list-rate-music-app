package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.NoteRepository;
import pl.coderslab.repository.UserAlbumRepository;
import pl.coderslab.api.ApiService;
import pl.coderslab.model.*;

import java.util.*;


@Controller
public class UserAlbumController {

    private final UserAlbumRepository userAlbumRepository;
    private final NoteRepository noteRepository;
    private final ApiService apiService;

    public UserAlbumController(UserAlbumRepository userAlbumRepository, NoteRepository noteRepository, ApiService apiService) {
        this.userAlbumRepository = userAlbumRepository;
        this.noteRepository = noteRepository;
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
            userAlbum.setAlbumCover(data.getStrAlbumThumb());
        });

        Note note = new Note();
        note.setIdAlbum(userAlbum.getIdAlbum());
        note.setDescription("Your note is empty");
        noteRepository.save(note);

        userAlbum.setNote(note);
        userAlbumRepository.save(userAlbum);
        return "redirect:/albums?idArtist=" + idArtist;
    }

    //album -- list
    @GetMapping("/albums/saved")
    public String displaySavedAlbums(Model model) {
        List<UserAlbum> userAlbums = userAlbumRepository.findAll();
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        model.addAttribute("updateNote", new Note());
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

    @PostMapping("/albums/saved/editNote")
    public String editNote(Note note, Model model) {
        model.addAttribute("updateNote", note);
        Note albumNote = noteRepository.findByIdAlbum(note.getIdAlbum());
        albumNote.setDescription(note.getDescription());
        noteRepository.save(note);
        return "redirect:/albums/saved";
    }


}
