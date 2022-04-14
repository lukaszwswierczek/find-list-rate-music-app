package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Note;
import pl.coderslab.model.Rating;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findByIdAlbum(Long idAlbum);

}
