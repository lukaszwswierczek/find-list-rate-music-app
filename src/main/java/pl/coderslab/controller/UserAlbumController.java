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
import java.util.stream.Collectors;


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

        //current user
        User listener = customUser.getUser();

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

        //note creation
        Note note = new Note();
        note.setIdAlbum(userAlbum.getIdAlbum());
        note.setDescription("Your note is empty");
        noteRepository.save(note);
        userAlbum.setNote(note);

        //saving album
        userAlbumRepository.save(userAlbum);

        //saving album on user list
        List<UserAlbum> userAlbums = listener.getUserAlbums();
        userAlbums = userAlbums.stream()
                .filter(album -> !album.getIdAlbum().equals(userAlbum.getIdAlbum()))
                .collect(Collectors.toList());

        userAlbums.add(userAlbum);
        listener.setUserAlbums(userAlbums);
        userRepository.save(listener);

        return "redirect:/albums?idArtist=" + idArtist;
    }

    //album -- list
    @GetMapping("/albums/saved")
    public String displaySavedAlbums(Model model,
                                     @AuthenticationPrincipal CurrentUser customUser) {
        //current user list
        User listener = customUser.getUser();
        List<UserAlbum> listenerUserAlbums = listener.getUserAlbums();

        model.addAttribute("updateNote", new Note());
        model.addAttribute("userAlbums", listenerUserAlbums);
        return "savedAlbums";
    }


    //album -- delete
    @GetMapping("/album/delete")
    public String deleteAlbum(@RequestParam String idAlbum,
                              @AuthenticationPrincipal CurrentUser customUser) {

        //updating user's list
        User listener = customUser.getUser();
        List<UserAlbum> listenerAlbums = listener.getUserAlbums();
        List<UserAlbum> updatedAlbums = listenerAlbums.stream()
                .filter(album -> !album.getIdAlbum().equals(Long.valueOf(idAlbum)))
                .collect(Collectors.toList());
        listener.setUserAlbums(updatedAlbums);
        userRepository.save(listener);

        //deleting from albums table if no user has that album on their list
        UserAlbum album = userAlbumRepository.findById(Long.valueOf(idAlbum)).get();

        List<User> allUsers = userRepository.findAll();
        List<User> listOfUsersWithAlbum = allUsers.stream()
                .filter(user -> user.getUserAlbums().contains(album))
                .collect(Collectors.toList());
        if (listOfUsersWithAlbum.isEmpty()) {
            userAlbumRepository.delete(album);
        }

        return "redirect:/user/albums/saved";
    }

    @PostMapping("/albums/saved/editNote")
    public String editNote(Note note, Model model) {
        model.addAttribute("updateNote", note);
        Note albumNote = noteRepository.findByIdAlbum(note.getIdAlbum());
        albumNote.setDescription(note.getDescription());
        noteRepository.save(note);
        return "redirect:/user/albums/saved";
    }


}
