package pl.lukaszswierczek.findListRateApp.model;

import lombok.*;
import pl.lukaszswierczek.findListRateApp.user.User;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "albums")
@Data
public class UserAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idAlbum;
    private String artist;
    private String album;
    private String yearOfRelease;
    private String genre;
    private String albumCover;

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    @OneToOne
    private User user;
}
