package sample;

import javafx.scene.control.Alert;
import java.util.ArrayList;
import static sample.Controller.MAXROWS;

public class ConsumedMealList {
    static private ArrayList<ConsumedMeal> consumedMeals;
    static
    {
        consumedMeals = new ArrayList<>();
    }

    public static ArrayList<ConsumedMeal> getConsumedMeals() {
        return consumedMeals;
    }

    static public boolean add(ConsumedMeal consumedMeal)
    {

        if(consumedMeals.size() >= MAXROWS)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input data error");
            alert.setHeaderText("Consumed meals");
            alert.setContentText("Maximum meals for day is reached!");
            alert.showAndWait();
            return false;
        }
        consumedMeals.add(consumedMeal);
        return true;
    }

    static public boolean delete(int index)
    {
        if(consumedMeals.remove(index) != null)
            return true;
        else
            return false;
    }

    static public ConsumedMeal getSum()
    {
        ConsumedMeal sumConsuptedMeal;
        Double weight = 0.0;
        Double calories = 0.0;
        Double totalFat = 0.0;
        Double saturatedFat = 0.0;
        Double cholesterol = 0.0;
        Double totalCarbohydrate = 0.0;
        Double dietaryFiber = 0.0;
        Double sugar = 0.0;
        Double protein = 0.0;

        for(int i=0; i < consumedMeals.size(); i++)
        {
            calories += consumedMeals.get(i).getCalories();
            weight += consumedMeals.get(i).getWeight();
            totalFat += consumedMeals.get(i).getTotalFat();
            saturatedFat += consumedMeals.get(i).getSaturatedFat();
            cholesterol += consumedMeals.get(i).getCholesterol();
            totalCarbohydrate += consumedMeals.get(i).getTotalCarbohydrate();
            dietaryFiber += consumedMeals.get(i).getDietaryFiber();
            sugar += consumedMeals.get(i).getSugar();
            protein += consumedMeals.get(i).getProtein();
        }


        sumConsuptedMeal = new ConsumedMeal("sum", weight, calories, totalFat, saturatedFat, cholesterol, totalCarbohydrate, dietaryFiber, sugar, protein);
        return sumConsuptedMeal;
    }

}
