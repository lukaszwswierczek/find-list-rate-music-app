package pl.coderslab.model;

import lombok.*;
import pl.coderslab.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    private String rating;
    private String albumCover;

    @OneToOne
    private Note note;

    @OneToOne
    private User user;
}
