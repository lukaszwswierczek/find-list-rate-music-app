package pl.coderslab.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "tracks")
public class UserAlbumTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String artist;
    private String album;
    private String title;
    private String genre;
    private Integer duration;
    private String rating;

}
