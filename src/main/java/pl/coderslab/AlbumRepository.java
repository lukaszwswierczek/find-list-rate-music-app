package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.UserListAlbum;

public interface AlbumRepository extends JpaRepository<UserListAlbum, Long> {
}
