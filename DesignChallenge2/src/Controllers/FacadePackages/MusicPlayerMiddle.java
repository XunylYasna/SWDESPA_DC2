package Controllers.FacadePackages;

import Controllers.SongListViewCell;
import Database.SongListBuildTemp;
import Model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.ArrayList;

public class MusicPlayerMiddle {

    private ListView<Song> songlistView;
    private ArrayList<Song> songList;
    private SongListBuildTemp songListBuildTemp = new SongListBuildTemp();
    ObservableList list = FXCollections.observableArrayList();
    private Song songSelected;

    public MusicPlayerMiddle(ListView<Song> songlistView) {
        this.songlistView = songlistView;
    }

    public void initialize(String username){
        //      Song load
        songList = songListBuildTemp.getSongs();
        list.removeAll();
        list.addAll(songList);
        songlistView.setItems(list);

        songlistView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                return new SongListViewCell();
            }
        });
    }

    public Song getSongselected(){
        songSelected = songlistView.getSelectionModel().getSelectedItem();
        return songSelected;
    }

    public void addnewSong(Song newSong){
        list.add(newSong);
        songList.add(newSong);
        this.refreshListView();
    }

    public void refreshListView(){
        songlistView.refresh();
        songlistView.getItems().removeAll();
        songlistView.setItems(list);
    }

    public Song getPrevSong(){
        ObservableList<Integer> indices = songlistView.getSelectionModel().getSelectedIndices();
        Song prevSong = null;
        if(indices.get(0) - 1 != -1){
            songlistView.getSelectionModel().select(indices.get(0)-1);
            songSelected = songlistView.getSelectionModel().getSelectedItem();
            prevSong = songSelected;
        }

        return prevSong;
    }

    public Song getNextSong(){
        ObservableList<Integer> indices = songlistView.getSelectionModel().getSelectedIndices();
        Song nextSong = null;

        if(indices.get(0) + 1 < list.size()){
            songlistView.getSelectionModel().select(indices.get(0)+1);
            songSelected = songlistView.getSelectionModel().getSelectedItem();
            nextSong = songSelected;
        }

        return nextSong;
    }

    public Song getSongSelected(){
        return songSelected;
    }
}
