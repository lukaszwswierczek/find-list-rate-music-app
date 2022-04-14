package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ratings")
public class Rating {

    @Id
    private Long idTrack;
    private String rating;

//    @OneToOne
//    private UserTrack userTrack;


}
