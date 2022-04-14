package pl.coderslab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "notes")
public class Note {

    @Id
    private Long idAlbum;
    private String description;
}
