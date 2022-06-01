package pl.lukaszswierczek.findListRateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukaszswierczek.findListRateApp.model.Rating;
import pl.lukaszswierczek.findListRateApp.user.User;

public interface RatingRepository extends JpaRepository <Rating, Long> {

    Rating findRatingByUserAndIdTrack(User user, Long idTrack);
}
