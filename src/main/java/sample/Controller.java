package sample;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {

    @FXML
    private TextArea txtResult;

    @FXML
    public void setAcdpDirectory(Event e){

        System.out.println("Button clicked");
        txtResult.setText("First Text");
    }
}
