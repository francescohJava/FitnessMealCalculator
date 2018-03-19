package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMealsDialog {

    public void display() throws Exception
    {
        Stage window = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("addMealsDialog.fxml"));
        window.setScene(new Scene(root, 300, 420));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add meals");
        window.showAndWait();


    }
}
