package pl.lukaszswierczek.findListRateApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lukaszswierczek.findListRateApp.user.User;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idAlbum;
    private String description;

    @OneToOne
    private User user;
}
