package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RepairMealsDialog {

    public void display() throws Exception
    {
        Stage window = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("repairMealsDialog.fxml"));
        window.setScene(new Scene(root, 450, 420));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Repair meals");
        window.showAndWait();

    }


}
