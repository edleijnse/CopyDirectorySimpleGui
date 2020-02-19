package sample;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import leijnse.info.AcdpAccessor;
import leijnse.info.ImageRow;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

public class Controller {

    @FXML
    private ListView listItems = new ListView();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File choosedDirectory =new File("dummy");

    @FXML
    private TextField txtSearchKeywords = new TextField();

    @FXML
    private Label lblAcdpDirectory = new Label();

    @FXML
    public void setAcdpDirectory(Event e){

        System.out.println("Button setAcdpDiretory clicked");
        configuringDirectoryChooser(directoryChooser);
        choosedDirectory = directoryChooser.showDialog(new Stage());
        String myChoosenDirectory = choosedDirectory.toPath().toString();
        System.out.println("choosedDirectory: " + myChoosenDirectory);
        lblAcdpDirectory.setText(myChoosenDirectory);


    }

    @FXML
    public void startSearch(Event e){
        System.out.println("Button startSearch clicked");
        AcdpAccessor acdpAccessor = new AcdpAccessor();
        listItems.getItems().clear();
        listItems.refresh();

        List<ImageRow> imageWithSomeKeywords = acdpAccessor.selectFromImageTable(true,"/media/psf/MyDrive01/acdp/acdpImage/layout", "-","-", BigInteger.valueOf(0),txtSearchKeywords.getText());
        imageWithSomeKeywords.forEach(imageRow -> {
            listItems.getItems().add(imageRow.getDirectory()+", "+imageRow.getFile()+", " + imageRow.getIptcKeywords());
        });
        listItems.refresh();


    }
    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for FileChooser
        directoryChooser.setTitle("Select Directory");

        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
