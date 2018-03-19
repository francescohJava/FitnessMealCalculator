package sample;

public class ConsumedMeal {
    private String name;
    private Double weight;
    private Double calories;
    private Double totalFat;
    private Double saturatedFat;
    private Double cholesterol;
    private Double totalCarbohydrate;
    private Double dietaryFiber;
    private Double sugar;
    private Double protein;

    public ConsumedMeal() {
    }

    public ConsumedMeal(String name, Double weight, Double calories, Double totalFat, Double saturatedFat, Double cholesterol, Double totalCarbohydrate, Double dietaryFiber, Double sugar, Double protein) {
        this.name = name;
        this.weight = weight;
        this.calories = round(calories);
        this.totalFat = round(totalFat);
        this.saturatedFat = round(saturatedFat);
        this.cholesterol = round(cholesterol);
        this.totalCarbohydrate = round(totalCarbohydrate);
        this.dietaryFiber = round(dietaryFiber);
        this.sugar = round(sugar);
        this.protein = round(protein);
    }

    public void fill(Meal meal, double weight)
    {
        this.name = meal.getName();
        this.calories = round(meal.getCalories()/100*weight);
        this.totalFat = round(meal.getTotalFat()/100*weight);
        this.saturatedFat = round(meal.getSaturatedFat()/100*weight);
        this.cholesterol = round(meal.getCholesterol()/100*weight);
        this.totalCarbohydrate = round(meal.getTotalCarbohydrate()/100*weight);
        this.dietaryFiber = round(meal.getDietaryFiber()/100*weight);
        this.sugar = round(meal.getSugar()/100*weight);
        this.protein = round(meal.getProtein()/100*weight);
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCalories() {
        return calories;
    }

    public Double getTotalFat() {
        return totalFat;
    }

    public Double getSaturatedFat() {
        return saturatedFat;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public Double getTotalCarbohydrate() {
        return totalCarbohydrate;
    }

    public Double getDietaryFiber() {
        return dietaryFiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public Double getProtein() {
        return protein;
    }

    private Double round(Double value)
    {
        if(value == null)
            return null;
        value = value*100;
        long temp = Math.round(value);
        return (double) temp/100;
    }
}
