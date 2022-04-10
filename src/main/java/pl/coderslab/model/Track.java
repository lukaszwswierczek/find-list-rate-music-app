package pl.coderslab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class Track {

    private String strTrack;
    private String intTrackNumber;
    private String idArtist;
    private String idAlbum;
    private String idTrack;
    private String strAlbum;
    private String strArtist;
    private String intDuration;
    private String strGenre;
}
