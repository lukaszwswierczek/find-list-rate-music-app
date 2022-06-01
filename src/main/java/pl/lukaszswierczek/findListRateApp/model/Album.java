package pl.lukaszswierczek.findListRateApp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Album {

    private String idArtist;
    private String strArtist;
    private String idAlbum;
    private String strAlbum;
    private String intYearReleased;
    private String strGenre;
    private String strAlbumThumb;
    private String strReleaseFormat;

}
