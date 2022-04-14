package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "albums")
@Data
public class UserAlbum {

    @Id
    @Column(name = "id")
    private Long idAlbum;
    private String artist;
    private String album;
    private String yearOfRelease;
    private String genre;
    private String rating;
    private String albumCover;

    @OneToOne
    private Note note;

}
