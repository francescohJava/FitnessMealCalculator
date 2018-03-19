package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import javafx.beans.binding.Bindings;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    final static int MAXROWS = 14;


    @FXML
    private ComboBox<String> mealComboBox;

    @FXML
    private TextField mealWeight;

    @FXML
    private TableView<ConsumedMeal> nutritionTable;

    public ComboBox<String> getMealComboBox() {
        return mealComboBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mealComboBox.getItems().addAll(MealList.toArrayStringName());


        MealList.changeListProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true)
                {
                    refreshMeals();
                }

            }
        });

        nutritionTable.setRowFactory(new Callback<TableView<ConsumedMeal>, TableRow<ConsumedMeal>>(){
            public TableRow<ConsumedMeal> call(TableView<ConsumedMeal> tableView)
            {
                final TableRow<ConsumedMeal> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem deleteMealItem = new MenuItem("delete meal");
                deleteMealItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        nutritionTable.getItems().remove(row.getItem());
                        ConsumedMealList.delete(row.getIndex());
                        refreshTable();
                    }
                });
                contextMenu.getItems().add(deleteMealItem);
                row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu)null)
                .otherwise(contextMenu));
                return row;
            }
        });
    }

    @FXML
    public void showAddMealsDialog() throws Exception {
        AddMealsDialog dialog = new AddMealsDialog();
        dialog.display();
    }

    @FXML
    public void showDeleteMealsDialog() throws Exception
    {
        DeleteMealsDialog dialog = new DeleteMealsDialog();
        dialog.display();
    }

    @FXML
    public void showRepairMealsDialog() throws Exception
    {
        RepairMealsDialog dialog = new RepairMealsDialog();
        dialog.display();

    }

    @FXML
    public void refreshMeals()
    {
        this.mealComboBox.getItems().clear();
        this.mealComboBox.getItems().addAll(MealList.toArrayStringName());
        MealList.setChangeList(false);
    }

    @FXML
    public void addConsumedMeal()
    {
        Double weight;
        ConsumedMeal consumptmeal = new ConsumedMeal();
        Meal meal = MealList.find(mealComboBox.getValue());
        if(meal == null)
            return;
        try{
            weight = Double.parseDouble(mealWeight.getText());
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input data error");
            alert.setHeaderText("Wrong format in weight!");
            alert.setContentText("Fill " + mealWeight.getId() + " correctly!");
            alert.showAndWait();
            mealWeight.setText("");
            return;
        }
        consumptmeal.fill(meal, weight);
        ConsumedMealList.add(consumptmeal);
        mealWeight.clear();
        refreshTable();
    }

    private void refreshTable() {
        ObservableList<ConsumedMeal> data = nutritionTable.getItems();
        int i;
        data.clear();
        int sizeList = ConsumedMealList.getConsumedMeals().size();
        for(i = 0; i < sizeList; i++)
        {
            data.add(ConsumedMealList.getConsumedMeals().get(i));
        }
        ConsumedMeal emptyMeal = new ConsumedMeal("", null, null, null, null, null, null, null, null, null);

        for(;i<MAXROWS; i++)
        {
            data.add(emptyMeal);
        }
        data.add(ConsumedMealList.getSum());
    }


}
