package pl.coderslab.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.model.Album;
import pl.coderslab.model.Artist;
import pl.coderslab.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//DATA TRANSFER OBJECT - obiekt domenowy
public class ApiDto {

    private List<Artist> artistList = new ArrayList<>();
    private List<Album> albums = new ArrayList<>();
    private List<Track> trackList = new ArrayList<>();

    @JsonProperty("artists")
    private void unpackNestedArtist(Map<String, Object>[] artists) {
        for (Map<String, Object> artistInfo : artists) {
            Artist tempArtist = new Artist();

            tempArtist.setIdArtist((String) artistInfo.get("idArtist"));
            tempArtist.setStrArtist((String) artistInfo.get("strArtist"));
            tempArtist.setStrGenre((String) artistInfo.get("strGenre"));
            tempArtist.setStrArtistThumb((String) artistInfo.get("strArtistThumb"));
            tempArtist.setStrBiographyEN((String) artistInfo.get("strBiographyEN"));

            artistList.add(tempArtist);
        }
    }

    @JsonProperty("album")
    private void unpackNestedAlbums(Map<String, Object>[] album) {
        for (Map<String, Object> albumInfo : album) {
            Album tempAlbum = new Album();

            tempAlbum.setStrAlbum((String) albumInfo.get("strAlbum"));
            tempAlbum.setStrArtist((String) albumInfo.get("strArtist"));
            tempAlbum.setIdArtist((String) albumInfo.get("idArtist"));
            tempAlbum.setIntYearReleased((String) albumInfo.get("intYearReleased"));
            tempAlbum.setIdAlbum((String) albumInfo.get("idAlbum"));
            tempAlbum.setStrGenre((String) albumInfo.get("strGenre"));
            tempAlbum.setStrAlbumThumb((String) albumInfo.get("strAlbumThumb"));
            tempAlbum.setStrReleaseFormat((String) albumInfo.get("strReleaseFormat"));

            albums.add(tempAlbum);
        }
    }

    @JsonProperty("track")
    private void unpackNestedTracks(Map<String, Object>[] track) {
        for (Map<String, Object> trackInfo : track) {
            Track tempTrack = new Track();

            tempTrack.setStrTrack((String) trackInfo.get("strTrack"));
            tempTrack.setIntTrackNumber((String) trackInfo.get("intTrackNumber"));
            tempTrack.setIdAlbum((String) trackInfo.get("idAlbum"));
            tempTrack.setIdArtist((String) trackInfo.get("idArtist"));
            tempTrack.setIdTrack((String) trackInfo.get("idTrack"));
            tempTrack.setStrAlbum((String) trackInfo.get("strAlbum"));
            tempTrack.setStrArtist((String) trackInfo.get("strArtist"));
            tempTrack.setIntDuration((String) trackInfo.get("intDuration"));
            tempTrack.setStrGenre((String) trackInfo.get("strGenre"));

            trackList.add(tempTrack);
        }
    }

}