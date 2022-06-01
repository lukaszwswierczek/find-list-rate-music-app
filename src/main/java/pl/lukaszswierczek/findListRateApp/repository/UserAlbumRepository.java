package pl.lukaszswierczek.findListRateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.lukaszswierczek.findListRateApp.model.UserAlbum;
import pl.lukaszswierczek.findListRateApp.user.User;

import java.util.List;

public interface UserAlbumRepository extends JpaRepository<UserAlbum, Long> {

    List<UserAlbum> findByUser(User user);

    @Query("select u from UserAlbum u where u.user = ?1 and u.idAlbum = ?2")
    UserAlbum findUserAlbumByUserAndIdAlbum(User user, Long idAlbum);
}
