package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RepairMealsController implements Initializable {

    private int order = 0;

    @FXML
    private ComboBox<String> mealComboBox;

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
    private Button repairButton = new Button();

    @FXML
    private Button exitButton = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repairButton.setDisable(true);
        mealComboBox.getItems().addAll(MealList.toArrayStringName());
    }

    @FXML
    private void selectItem()
    {
        Meal meal = MealList.find(mealComboBox.getValue());
        order = MealList.indexOf(meal);
        if(order == -1)
            return;
        name.setText(meal.getName());
        calories.setText(meal.getCalories().toString());
        totalFat.setText(meal.getTotalFat().toString());
        saturatedFat.setText(meal.getSaturatedFat().toString());
        cholesterol.setText(meal.getCholesterol().toString());
        totalCarbohydrate.setText(meal.getTotalCarbohydrate().toString());
        dietaryFiber.setText(meal.getDietaryFiber().toString());
        sugar.setText(meal.getSugar().toString());
        protein.setText(meal.getProtein().toString());
        repairButton.setDisable(false);
    }

    @FXML
    private void repairMeal()
    {
        Meal meal = new Meal();
        meal.setName(name.getText());
        meal.setCalories(getTextFieldValue(calories));
        meal.setTotalFat(getTextFieldValue(totalFat));
        meal.setSaturatedFat(getTextFieldValue(saturatedFat));
        meal.setCholesterol(getTextFieldValue(cholesterol));
        meal.setTotalCarbohydrate(getTextFieldValue(totalCarbohydrate));
        meal.setDietaryFiber(getTextFieldValue(dietaryFiber));
        meal.setSugar(getTextFieldValue(sugar));
        meal.setProtein(getTextFieldValue(protein));

        if(MealList.repair(meal,order) == true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Repair meals");
            alert.setContentText("Meal repaired successfully!");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Repair meals");
            alert.setContentText("Repair meal failed!");
            alert.showAndWait();
        }

    }

    @FXML
    private void saveAndExit()
    {
        MealList.writeMealXML();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        MealList.setChangeList(true);
        stage.close();
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

}
