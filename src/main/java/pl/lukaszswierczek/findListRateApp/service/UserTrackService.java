package pl.lukaszswierczek.findListRateApp.service;

import org.springframework.stereotype.Service;
import pl.lukaszswierczek.findListRateApp.model.Rating;
import pl.lukaszswierczek.findListRateApp.model.Track;
import pl.lukaszswierczek.findListRateApp.model.UserTrack;
import pl.lukaszswierczek.findListRateApp.repository.RatingRepository;
import pl.lukaszswierczek.findListRateApp.repository.UserTrackRepository;
import pl.lukaszswierczek.findListRateApp.user.User;

import java.util.List;

@Service
public class UserTrackService {

    private final ApiService apiService;
    private final UserTrackRepository userTrackRepository;
    private final RatingRepository ratingRepository;

    public UserTrackService(ApiService apiService, UserTrackRepository userTrackRepository, RatingRepository ratingRepository) {
        this.apiService = apiService;
        this.userTrackRepository = userTrackRepository;
        this.ratingRepository = ratingRepository;
    }

    public void executeNewUserTrack(User listener, String idTrack) {
        if (userTrackRepository.findUserTrackByUserAndIdTrack(listener, Long.parseLong(idTrack)) == null) {
            UserTrack track = createUserTrack(listener, idTrack);
            //rating creation
            Rating rating = createRating(listener, track);
            track.setRating(rating);
            //saving track
            userTrackRepository.save(track);
        }
    }

    private UserTrack createUserTrack(User listener, String idTrack) {
        List<Track> tracks = apiService.getSpecificTrack(idTrack);
        UserTrack track = new UserTrack();
        tracks.stream().forEach(data -> {
            track.setIdTrack(Long.parseLong(data.getIdTrack()));
            track.setAlbum(data.getStrAlbum());
            track.setIdAlbum(Long.parseLong(data.getIdAlbum()));
            track.setArtist(data.getStrArtist());
            track.setGenre(data.getStrGenre());
            track.setTitle(data.getStrTrack());
            //formatting duration
            String duration = data.getIntDuration().substring(0, 3);
            track.setDuration(String.format("%02d:%02d", Integer.parseInt(duration) / 60, Integer.parseInt(duration) % 60));
            track.setUser(listener);
        });
        return track;
    }

    private Rating createRating(User listener, UserTrack track) {
        Rating rating = new Rating();
        rating.setIdTrack(track.getIdTrack());
        rating.setRating("0");
        rating.setUser(listener);
        ratingRepository.save(rating);
        return rating;
    }


}
