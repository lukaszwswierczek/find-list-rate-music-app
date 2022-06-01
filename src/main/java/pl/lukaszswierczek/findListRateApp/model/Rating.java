package pl.lukaszswierczek.findListRateApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lukaszswierczek.findListRateApp.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idTrack;
    private String rating;

    @OneToOne
    private User user;


}
