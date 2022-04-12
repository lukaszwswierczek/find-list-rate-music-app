package pl.coderslab.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "tracks")
public class UserTrack {

    @Id
    private String idTrack;
    private String idAlbum;
    private String artist;
    private String album;
    private String title;
    private String genre;
    private String duration;
    private String rating;

}
