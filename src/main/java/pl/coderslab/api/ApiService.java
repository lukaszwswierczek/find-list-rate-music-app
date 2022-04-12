package pl.coderslab.api;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.model.Album;
import pl.coderslab.model.Artist;
import pl.coderslab.model.Track;

import java.util.List;

@Service
public class ApiService {

    private final String API_URL_DISCOGRAPHY = "https://theaudiodb.com/api/v1/json/2/album.php?i=";
    private final String API_URL_ARTIST_INFO = "https://theaudiodb.com/api/v1/json/2/search.php?s=";
    private final String API_URL_TRACKLIST = "https://theaudiodb.com/api/v1/json/2/track.php?m=";
    private final String API_URL_ALBUM_INFO = "https://theaudiodb.com/api/v1/json/2/album.php?m=";
    private final String API_URL_TRACK_INFO = "https://theaudiodb.com/api/v1/json/2/track.php?h=";

    public List<Artist> getArtistInfo(String artistName) throws HttpMessageNotReadableException {

        String query = API_URL_ARTIST_INFO + artistName;
        ApiDto apiDto = getDto(query);
        assert apiDto != null : "Returned JSON is null";
        return apiDto.getArtistList();
    }

    public List<Album> getAlbumsByArtist(String idArtist) {

        String query = API_URL_DISCOGRAPHY + idArtist;
        ApiDto apiDto = getDto(query);
        assert apiDto != null : "Returned JSON is null";
        return apiDto.getAlbums();
    }

    public List<Album> getSpecificAlbum(String idAlbum) {

        String query = API_URL_ALBUM_INFO + idAlbum;
        ApiDto apiDto = getDto(query);
        assert apiDto != null : "Returned JSON is null";
        return apiDto.getAlbums();
    }

    public List<Track> getTrackList(String idAlbum) {

        String query = API_URL_TRACKLIST + idAlbum;
        ApiDto apiDto = getDto(query);
        assert apiDto != null : "Returned JSON is null";
        return apiDto.getTrackList();
    }

    public List<Track> getSpecificTrack(String idTrack) {

        String query = API_URL_TRACK_INFO + idTrack;
        ApiDto apiDto = getDto(query);
        assert apiDto != null : "Returned JSON is null";
        return apiDto.getTrackList();
    }

    private ApiDto getDto(String queryUrl) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(queryUrl, ApiDto.class)
                .getBody();

    }
}