package Controllers;

import javafx.fxml.FXML;

public class cellController {
    @FXML
    public void dragDetect(){
        System.out.println("detect");
    }

    @FXML
    public void dragDone(){
        System.out.println("done");
    }

    @FXML
    public void dragDrop(){
        System.out.println("drop");
    }

    @FXML
    public void dragEntered(){
        System.out.println("entered");
    }

    @FXML
    public void dragExited(){
        System.out.println("exited");
    }
}
