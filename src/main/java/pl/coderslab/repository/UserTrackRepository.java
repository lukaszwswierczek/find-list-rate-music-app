package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.UserTrack;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
}
