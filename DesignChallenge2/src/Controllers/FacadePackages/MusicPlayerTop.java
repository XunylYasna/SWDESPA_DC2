package Controllers.FacadePackages;

import Model.Song;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MusicPlayerTop {

    //    Top UI
    private Labeled selectedTitleLbl;
    private Labeled selectedArtistLbl;
    private Labeled selectedGenreLbl;
    private Labeled selectedAlbumLbl;
    private Labeled selectedFromLbl;
    private ImageView acoverImg;

    public MusicPlayerTop(Labeled selectedTitleLbl, Labeled selectedArtistLbl, Labeled selectedGenreLbl, Labeled selectedAlbumLbl, Labeled selectedFromLbl, ImageView acoverImg) {
        this.selectedTitleLbl = selectedTitleLbl;
        this.selectedArtistLbl = selectedArtistLbl;
        this.selectedGenreLbl = selectedGenreLbl;
        this.selectedAlbumLbl = selectedAlbumLbl;
        this.selectedFromLbl = selectedFromLbl;
        this.acoverImg = acoverImg;
    }

    public void initialize(String title, String artist, String genre, String album, String from, Image acover){
        this.selectedTitleLbl.setText(title);
        this.selectedArtistLbl.setText(artist);
        this.selectedGenreLbl.setText(genre);
        this.selectedAlbumLbl.setText(album);
        this.selectedFromLbl.setText(from);
        this.acoverImg.setImage(acover);
    }

    public void initialize(Song song, String from, Image acover){
        this.selectedTitleLbl.setText(song.getSongTitle());
        this.selectedArtistLbl.setText(song.getArtist());
        this.selectedGenreLbl.setText(song.getGenre());
        this.selectedAlbumLbl.setText(song.getAlbum());
        this.selectedFromLbl.setText(from);
        this.acoverImg.setImage(acover);
    }
}