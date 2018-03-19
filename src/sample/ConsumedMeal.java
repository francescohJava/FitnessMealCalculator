package sample;

public class ConsumedMeal extends Meal {
    private Double weight;
    public ConsumedMeal() {
    }

    public ConsumedMeal(String name, Double weight, Double calories, Double totalFat, Double saturatedFat, Double cholesterol, Double totalCarbohydrate, Double dietaryFiber, Double sugar, Double protein) {
        super(name, calories, totalFat, saturatedFat, cholesterol, totalCarbohydrate, dietaryFiber, sugar, protein);
        this.weight = weight;
            }

    public void fill(Meal meal, double weight)
    {
        fillMeal(meal);
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    private Double round(Double value)
    {
        if(value == null || value == 0.0)
            return null;
        value = value*100;
        long temp = Math.round(value);
        return (double) temp/100;
    }
}
