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
    private Long idTrack;
    private Long idAlbum;
    private String artist;
    private String album;
    private String title;
    private String genre;
    private String duration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratings_id", unique = true)
    private Rating rating;
}
