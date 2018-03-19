package sample;

//Class Meal contains nutrition values for meal
public class Meal {

    private String name;
    private Double calories;
    private Double totalFat;
    private Double saturatedFat;
    private Double cholesterol;
    private Double totalCarbohydrate;
    private Double dietaryFiber;
    private Double sugar;
    private Double protein;

    public Meal() {
    }

    public Meal(Meal meal)
    {
        this.name = meal.name;
        this.calories = meal.calories;
        this.totalFat = meal.totalFat;
        this.saturatedFat = meal.saturatedFat;
        this.cholesterol = meal.cholesterol;
        this.totalCarbohydrate = meal.totalCarbohydrate;
        this.dietaryFiber = meal.dietaryFiber;
        this.sugar = meal.sugar;
        this.protein = meal.protein;
    }

    public Meal(String name, Double calories, Double totalFat, Double saturatedFat, Double cholesterol, Double totalCarohydrate, Double dietaryFiber, Double sugar, Double protein) {
        this.name = name;
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.cholesterol = cholesterol;
        this.totalCarbohydrate = totalCarohydrate;
        this.dietaryFiber = dietaryFiber;
        this.sugar = sugar;
        this.protein = protein;
    }



    public Meal(String name, Double calories, Double totalFat, Double cholesterol, Double totalCarohydrate, Double protein) {
        this.name = name;

        this.calories = calories;
        this.totalFat = totalFat;
        this.cholesterol = cholesterol;
        this.totalCarbohydrate = totalCarohydrate;
        this.protein = protein;
    }

    public void fillMeal(Meal meal) {

        this.name = meal.name;
        this.calories = meal.calories;
        this.totalFat = meal.totalFat;
        this.saturatedFat = meal.saturatedFat;
        this.cholesterol = meal.cholesterol;
        this.totalCarbohydrate = meal.totalCarbohydrate;
        this.dietaryFiber = meal.dietaryFiber;
        this.sugar = meal.sugar;
        this.protein = meal.protein;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public void setTotalFat(Double totalFat) {
        this.totalFat = totalFat;
    }

    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setTotalCarbohydrate(Double totalCarohydrate) {
        this.totalCarbohydrate = totalCarohydrate;
    }

    public void setDietaryFiber(Double dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", totalFat=" + totalFat +
                ", saturatedFat=" + saturatedFat +
                ", cholesterol=" + cholesterol +
                ", totalCarohydrate=" + totalCarbohydrate +
                ", dietaryFiber=" + dietaryFiber +
                ", sugar=" + sugar +
                ", protein=" + protein +
                '}';
    }
}
