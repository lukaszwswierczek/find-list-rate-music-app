package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.UserAlbum;

public interface UserAlbumRepository extends JpaRepository<UserAlbum, Long> {
}
