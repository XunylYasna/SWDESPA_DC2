package Controllers.FacadePackages;

import Database.SongListBuildTemp;
import Model.Song;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AccountPane {

    User user;

    TextField firstnameTf;
    TextField lastnameTf;
    TextField emailTf;

    ListView<String> favoritesListView;
    ObservableList<String> observableList = FXCollections.observableArrayList();
    ArrayList<Song> songList;


    public AccountPane(User user, TextField firstnameTf, TextField lastnameTf, TextField emailTf, ListView<String> favoritesListView) {
       this.user = user;
        this.firstnameTf = firstnameTf;
        this.lastnameTf = lastnameTf;
        this.emailTf = emailTf;
        this.favoritesListView = favoritesListView;
    }


    public void setFavoritesListView(){
        firstnameTf.setText(user.getFirstname());
        lastnameTf.setText(user.getLastname());
        emailTf.setText(user.getEmail());

        SongListBuildTemp songListBuildTemp = new SongListBuildTemp();

        songList = songListBuildTemp.getFavorites(user.getUserID());
        for(int i =0; i < songList.size(); i++){
            observableList.add(songList.get(i).getSongTitle());
        }

        favoritesListView.setItems(observableList);
    }
}
