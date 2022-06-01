package pl.lukaszswierczek.findListRateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.lukaszswierczek.findListRateApp.model.UserTrack;
import pl.lukaszswierczek.findListRateApp.user.User;

import java.util.List;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {

    List<UserTrack> findByUser(User user);

    @Query("select u from UserTrack u where u.user = ?1 and u.idTrack = ?2")
    UserTrack findUserTrackByUserAndIdTrack(User user, Long idAlbum);
}
