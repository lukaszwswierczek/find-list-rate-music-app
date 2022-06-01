package pl.lukaszswierczek.findListRateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukaszswierczek.findListRateApp.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findByIdAlbum(Long idAlbum);

}
