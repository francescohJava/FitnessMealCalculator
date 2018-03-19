package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteMealsDialog {


    public void display() throws Exception
    {
        Stage window = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("deleteMealsDialog.fxml"));
        window.setScene(new Scene(root, 450, 420));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete meals");
        window.showAndWait();


    }
}
