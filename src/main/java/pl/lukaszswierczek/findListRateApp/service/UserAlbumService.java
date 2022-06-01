package pl.lukaszswierczek.findListRateApp.service;

import org.springframework.stereotype.Service;
import pl.lukaszswierczek.findListRateApp.model.Album;
import pl.lukaszswierczek.findListRateApp.model.Note;
import pl.lukaszswierczek.findListRateApp.model.UserAlbum;
import pl.lukaszswierczek.findListRateApp.repository.NoteRepository;
import pl.lukaszswierczek.findListRateApp.repository.UserAlbumRepository;
import pl.lukaszswierczek.findListRateApp.user.User;

import java.util.List;

@Service
public class UserAlbumService {

    private final ApiService apiService;
    private final UserAlbumRepository userAlbumRepository;
    private final NoteRepository noteRepository;

    public UserAlbumService(ApiService apiService, UserAlbumRepository userAlbumRepository, NoteRepository noteRepository) {
        this.apiService = apiService;
        this.userAlbumRepository = userAlbumRepository;
        this.noteRepository = noteRepository;
    }

    public void executeNewUserAlbum(User listener, String idAlbum) {
        if (userAlbumRepository.findUserAlbumByUserAndIdAlbum(listener, Long.parseLong(idAlbum)) == null) {
            UserAlbum userAlbum = createUserAlbum(listener, idAlbum);
            //note creation
            Note note = createNote(userAlbum, listener);
            userAlbum.setNote(note);
            //saving album
            userAlbumRepository.save(userAlbum);
        }
    }

    private UserAlbum createUserAlbum(User listener, String idAlbum) {
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
            return userAlbum;
        }

    private Note createNote(UserAlbum userAlbum, User listener) {
        Note note = new Note();
        note.setIdAlbum(userAlbum.getIdAlbum());
        note.setDescription("Your note is empty");
        note.setUser(listener);
        noteRepository.save(note);
        return note;
    }
}

