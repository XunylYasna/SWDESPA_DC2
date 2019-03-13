package Model;

public class Song {
    private int songID;
    private String songTitle;
    private String artist;
    private String album;
    private String genre;

    public Song(int songID, String songTitle, String artist, String album, String genre) {
        this.songID = songID;
        this.songTitle = songTitle;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public int getSongID() {
        return songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
