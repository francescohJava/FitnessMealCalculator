package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMealsController {

    @FXML
    private TextField name;

    @FXML
    private TextField calories;

    @FXML
    private TextField totalFat;

    @FXML
    private TextField saturatedFat;

    @FXML
    private TextField cholesterol;

    @FXML
    private TextField totalCarbohydrate;

    @FXML
    private TextField dietaryFiber;

    @FXML
    private TextField sugar;

    @FXML
    private TextField protein;


    @FXML
    Button addButton = new Button();

    @FXML
    Button exitButton = new Button();

    @FXML
    private void addMeal()
    {
        Meal meal;

        if(name.getText() == "")
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input data error");
            alert.setHeaderText("Wrong format in input!");
            alert.setContentText("Name field not filled!");
            alert.showAndWait();
            return;
        }

        Double Dcalories = getTextFieldValue(calories);
        if(Dcalories == null)
            return;

        Double DtotalFat = getTextFieldValue(totalFat);
        if(DtotalFat == null)
            return;

        Double DsaturatedFat = getTextFieldValue(saturatedFat);
        if(DsaturatedFat == null)
            return;

        Double Dcholesterol = getTextFieldValue(cholesterol);
        if(Dcholesterol == null)
            return;

        Double DtotalCarbohydrate = getTextFieldValue(totalCarbohydrate);
        if(DtotalCarbohydrate == null)
            return;

        Double DdietaryFiber = getTextFieldValue(dietaryFiber);
        if(DdietaryFiber == null)
            return;

        Double Dsugar = getTextFieldValue(sugar);
        if(Dsugar == null)
            return;

        Double Dprotein = getTextFieldValue(protein);
        if(Dprotein == null)
            return;

        meal = new Meal(name.getText(), Dcalories, DtotalFat, DsaturatedFat, Dcholesterol, DtotalCarbohydrate, DdietaryFiber, Dsugar, Dprotein);
        if(MealList.add(meal))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add new meal");
            alert.setContentText("Meal added successfully.");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Add new meal failed");

            alert.showAndWait();
        }
    }

    private Double getTextFieldValue(TextField field)
    {
        Double value;
        try{
            value = Double.parseDouble(field.getText());
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input data error");
            alert.setHeaderText("Wrong format in input!");
            alert.setContentText("Fill " + field.getId() + " correctly!");
            alert.showAndWait();
            field.setText("");
            return null;
        }
        return value;
    }

    public void saveAndExit()
    {
        MealList.writeMealXML();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        MealList.setChangeList(true);
        stage.close();
    }
}
