package pl.coderslab.model;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.user.User;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "tracks")
public class UserTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idTrack;
    private Long idAlbum;
    private String artist;
    private String album;
    private String title;
    private String genre;
    private String duration;

    @OneToOne
    private Rating rating;

    @OneToOne
    private User user;
}
