package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name="albums")
@Data
public class UserAlbum {

    @Id
    @Column(name = "id")
    private Long idAlbum;
    //    @Column(name = "Artist")
    private String artist;
//    @Column(name = "Album")
    private String album;
//    @Column(name = "Year of release")
    private String yearOfRelease;
//    @Column(name = "Genre")
    private String genre;
//    @Column(name = "Rating")
    private String rating;



}
