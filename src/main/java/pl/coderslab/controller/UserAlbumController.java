package pl.coderslab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.NoteRepository;
import pl.coderslab.repository.UserAlbumRepository;
import pl.coderslab.api.ApiService;
import pl.coderslab.model.*;
import pl.coderslab.user.CurrentUser;
import pl.coderslab.user.User;
import pl.coderslab.user.UserRepository;

import java.util.*;


@Controller
@RequestMapping("/user")
public class UserAlbumController {

    private final UserAlbumRepository userAlbumRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final ApiService apiService;

    public UserAlbumController(UserAlbumRepository userAlbumRepository, NoteRepository noteRepository, UserRepository userRepository, ApiService apiService) {
        this.userAlbumRepository = userAlbumRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.apiService = apiService;
    }


    //album -- add
    @GetMapping("/album/add")
    public String addUserAlbum(@RequestParam String idArtist,
                               @RequestParam String idAlbum,
                               @AuthenticationPrincipal CurrentUser customUser) {

        //current user = listener
        User listener = customUser.getUser();

        if(userAlbumRepository.findUserAlbumByUserAndIdAlbum(listener,Long.parseLong(idAlbum)) == null){

        List<Album> albums = apiService.getSpecificAlbum(idAlbum);
        UserAlbum userAlbum = new UserAlbum();
        albums.stream().forEach(data -> {
            userAlbum.setArtist(data.getStrArtist());
            userAlbum.setAlbum(data.getStrAlbum());
            userAlbum.setGenre(data.getStrGenre());
            userAlbum.setYearOfRelease(data.getIntYearReleased());
            userAlbum.setIdAlbum(Long.valueOf(idAlbum));
            userAlbum.setAlbumCover(data.getStrAlbumThumb());
            userAlbum.setUser(listener);
        });

        //note creation
        Note note = new Note();
        note.setIdAlbum(userAlbum.getIdAlbum());
        note.setDescription("Your note is empty");
        note.setUser(listener);
        noteRepository.save(note);
        userAlbum.setNote(note);

        //saving album
        userAlbumRepository.save(userAlbum);}

        return "redirect:/albums?idArtist=" + idArtist;
    }

    //album -- list
    @GetMapping("/albums/saved")
    public String displaySavedAlbums(Model model,
                                     @AuthenticationPrincipal CurrentUser customUser) {
        //current user list
        List<UserAlbum> listenerAlbums = userAlbumRepository.findByUser(customUser.getUser());

        model.addAttribute("updateNote", new Note());
        model.addAttribute("userAlbums", listenerAlbums);
        return "savedAlbums";
    }


    //album -- delete
    @GetMapping("/album/delete")
    public String deleteAlbum(@RequestParam String id) {

        UserAlbum album = userAlbumRepository.findById(Long.parseLong(id)).get();
        userAlbumRepository.delete(album);

        return "redirect:/user/albums/saved";
    }

    //album -- update note
    @PostMapping("/albums/saved/editNote")
    public String editNote(Note note,
                           Model model) {

        model.addAttribute("updateNote", note);
        Note albumNote = noteRepository.findById(note.getId()).get();
        albumNote.setDescription(note.getDescription());
        noteRepository.save(albumNote);
        return "redirect:/user/albums/saved";
    }


}
