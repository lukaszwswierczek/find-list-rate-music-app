package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Rating;
import pl.coderslab.model.UserTrack;

public interface RatingRepository extends JpaRepository <Rating, Long> {

    Rating findByIdTrack(Long idTrack);
}
