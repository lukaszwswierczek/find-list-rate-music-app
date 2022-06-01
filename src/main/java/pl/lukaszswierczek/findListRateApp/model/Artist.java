package pl.lukaszswierczek.findListRateApp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class Artist {

private String idArtist;
private String strArtist;
private String strGenre;
private String strBiographyEN;
private String strArtistThumb;

}
