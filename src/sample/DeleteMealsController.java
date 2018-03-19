package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteMealsController implements Initializable {

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
    private Button deleteButton = new Button();

    @FXML
    private Button exitButton = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteButton.setDisable(true);
        mealComboBox.getItems().addAll(MealList.toArrayStringName());

    }

    @FXML
    private void selectItem()
    {
        Meal meal = MealList.find(mealComboBox.getValue());
        if(meal == null)
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
        deleteButton.setDisable(false);
    }

    @FXML
    private void deleteMeal()
    {
        Meal meal = MealList.find(mealComboBox.getValue());
        MealList.delete(meal);


        name.clear();
        calories.clear();
        totalFat.clear();
        saturatedFat.clear();
        cholesterol.clear();
        totalCarbohydrate.clear();
        dietaryFiber.clear();
        sugar.clear();
        protein.clear();


        if(mealComboBox.getItems().remove(meal.getName()))
        {
            System.out.println("Deleting successfull");
        }
        else
        {
            System.out.println("Deleting failed");
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


}
