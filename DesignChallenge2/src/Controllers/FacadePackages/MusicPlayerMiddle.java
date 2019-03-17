package Controllers.FacadePackages;

import Controllers.SongListViewCell;
import Database.SongListBuildTemp;
import Model.Song;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.ArrayList;

public class MusicPlayerMiddle {

    private ArrayList<Song> songList;
    private Song songSelected;
    String filter;

    private ArrayList<String> stringList;

    private SongListBuildTemp songListBuildTemp = new SongListBuildTemp();

    //    ListView Related
    ObservableList songOL = FXCollections.observableArrayList();
    private ListView<Song> songlistView;

    //    GridPane Related
    private GridPane gridPane;

    //    Para sa stackpane
    private Pane listViewPane;
    private Pane tableViewPane;

    public MusicPlayerMiddle(ListView<Song> songlistView, GridPane gridPane, Pane listViewPane, Pane tableViewPane) {
        this.songlistView = songlistView;
        this.gridPane = gridPane;
        this.listViewPane = listViewPane;
        this.tableViewPane = tableViewPane;
    }

    public void initialize(String filtercolumn, String value){
        //      Song load

        songList = songListBuildTemp.getSongs(filtercolumn,value);
        songOL.removeAll();
        songOL.clear();
        songOL.addAll(songList);
        refreshListView();

        songlistView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                return new SongListViewCell();
            }
        });
    }

    public void setGridPane(String mode){
        filter = mode;
        stringList = songListBuildTemp.getColumn(mode);
        listViewPane.setVisible(false);
        tableViewPane.setVisible(true);

        gridPane.getChildren().clear();

        int column = 0;
        int row = 0;
        for (row = 0; row < stringList.size()/3; row++) {
            for (column = 0; column < 3; column++) {
                JFXButton button = new JFXButton(stringList.get(column + row));

//                Add Class
                button.getStyleClass().add("grid-Btn");

                button.setOnAction(event -> {
                    this.initialize(filter, button.getText());
                    listViewPane.setVisible(true);
                    tableViewPane.setVisible(false);
                });

                gridPane.add(button, column, row);
            }
        }

        for(int c = 0; c < stringList.size() % 3; c++){
            JFXButton button = new JFXButton(stringList.get(column + row - 1));
            button.getStyleClass().add("grid-Btn");

            button.setOnAction(event -> {
                this.initialize(filter, button.getText());
                listViewPane.setVisible(true);
                tableViewPane.setVisible(false);
            });

            gridPane.add(button, c, row);
        }



    }

    public Song getSongselected(){
        songSelected = songlistView.getSelectionModel().getSelectedItem();
        return songSelected;
    }

    public void addnewSong(Song newSong){
        songOL.add(newSong);
        songList.add(newSong);
        this.refreshListView();
    }

    public void refreshListView(){
        songlistView.refresh();
        songlistView.getItems().removeAll();
        songlistView.setItems(songOL);
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

        if(indices.get(0) + 1 < songOL.size()){
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
