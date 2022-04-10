package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name="albums")
@Data
public class UserListAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(name = "Artist")
    private String artistName;
//    @Column(name = "Album")
    private String albumTitle;
//    @Column(name = "Year of release")
    private String yearOfRelease;
//    @Column(name = "Genre")
    private String genre;
//    @Column(name = "Rating")
    private String rating;
    private String idAlbum;

    @OneToMany
    private List<UserAlbumTrack> tracks =
            new ArrayList<>();
}
