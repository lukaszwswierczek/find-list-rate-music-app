package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.UserAlbumTrack;

@Repository
public interface TrackRepository extends JpaRepository<UserAlbumTrack, Long> {
}
