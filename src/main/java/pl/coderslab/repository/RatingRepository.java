package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Rating;
import pl.coderslab.model.UserTrack;
import pl.coderslab.user.User;

public interface RatingRepository extends JpaRepository <Rating, Long> {

    Rating findRatingByUserAndIdTrack(User user, Long idTrack);
}
