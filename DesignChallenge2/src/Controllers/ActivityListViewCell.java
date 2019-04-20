package Controllers;

import Model.EventLogs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ActivityListViewCell extends ListCell<EventLogs> {

    @FXML
    private AnchorPane cellAnchor;
    @FXML
    private Labeled userlbl;
    @FXML
    private Labeled activitylbl;
    @FXML
    private Labeled timelbl;

    FXMLLoader mLLoader;

    @Override
    protected void updateItem(EventLogs eventLogs, boolean empty) {
        super.updateItem(eventLogs, empty);

        if (empty || eventLogs == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("Views/fxml/activityCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            userlbl.setText(eventLogs.getUsername());
            activitylbl.setText(eventLogs.getActivity());
            timelbl.setText(eventLogs.getDate());

            setText(null);
            setGraphic(cellAnchor);
        }
    }
}
