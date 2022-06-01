package pl.lukaszswierczek.findListRateApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import pl.lukaszswierczek.findListRateApp.model.Album;
import pl.lukaszswierczek.findListRateApp.model.Note;
import pl.lukaszswierczek.findListRateApp.model.UserAlbum;
import pl.lukaszswierczek.findListRateApp.repository.NoteRepository;
import pl.lukaszswierczek.findListRateApp.service.UserAlbumService;
import pl.lukaszswierczek.findListRateApp.user.CurrentUser;
import pl.lukaszswierczek.findListRateApp.user.User;
import pl.lukaszswierczek.findListRateApp.repository.UserAlbumRepository;
import pl.lukaszswierczek.findListRateApp.service.ApiService;

import java.util.*;


@Controller
@RequestMapping("/user")
public class UserAlbumController {

    private final UserAlbumRepository userAlbumRepository;
    private final NoteRepository noteRepository;
    private final UserAlbumService userAlbumService;

    public UserAlbumController(UserAlbumRepository userAlbumRepository, NoteRepository noteRepository, UserAlbumService userAlbumService) {
        this.userAlbumRepository = userAlbumRepository;
        this.noteRepository = noteRepository;
        this.userAlbumService = userAlbumService;
    }

    //album -- add
    @GetMapping("/album/add")
    public String addUserAlbum(@RequestParam String idArtist,
                               @RequestParam String idAlbum,
                               @AuthenticationPrincipal CurrentUser customUser) {
        //current user = listener
        User listener = customUser.getUser();
        userAlbumService.executeNewUserAlbum(listener, idAlbum);
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
