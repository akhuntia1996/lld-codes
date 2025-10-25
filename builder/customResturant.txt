Problem Statement:

Design a system for a fast-food restaurant where customers can order customized meals. Each meal can consist of a main item (e.g., burger, sandwich), a side item (e.g., fries, salad), and a drink (e.g., soda, juice). Customers should be able to build their own meals by selecting the items they want.

You need to design the system such that new meal combinations can be added easily without changing existing code. The system should also be able to produce predefined meals (e.g., "Kids Meal", "Vegan Meal", "Family Combo").

Models ---
User
Customer
Admin

ResturantDirector

Builder - setMainItem(), setSideItem(), setDrink(), calculateCost()
CustomBuilder
KidsBuilder
VeganBuilder
FamilyBuilder

Food - id, name, price per piece, quantity

Code ....
enum UserType {
    CUSTOMER, ADMIN
}
class User {
    int id, String name, String username, String password
    UserType userType
}
class Customer extends User {}
class Admin extends User {}

class Food {
    int id, String name, int quantity, double price
}

interface Builder {
    showSuggestedMeals();
    setMainItem(Food)
    setSideItem(Food)
    setDrink(Food)
    calculate()
} 
class Meal {
    Food main, side, drink;
    double price;
}
class CustomBuilder implements Builder {
    Map<Integer, Meal> meals = new HashMap<>();
    Meal meal = new Meal();
    showSuggestedMeals(){
        return meals;
    }
    chooseMeal(id){
        meal = meals.get(id);
    }
    setMainItem(Food) {
        this.meal.setMain(food);
    }
    ....
    calculate() {
        return meal.main.price, meal.side.price, meal.drink.price;
    }
}
class KidsBuilder implements Builder {
    Map<Integer, Meal> meals = new HashMap<>();
    Meal meal = new Meal();
    KidsBuilder(meals) {
        this.meals = meals;
    }
    showSuggestedMeals(){
        return meals;
    }
    chooseMeal(id){
        meal = meals.get(id);
    }
    setMainItem().
    ....
}
class VeganBuilder implements Builder {}
class FamilyBuilder implements Builder {}

class ResturantDirector {

    Builder mealBuilder = new Builder();
    switch(OPTION) {
        case OPTION.KIDS :
            mealBuilder = new KidsBuilder();
        case OPTION.VEGAN :
            mealBuilder = new VeganBuilder();
        case OPTION.FAMILY :
            mealBuilder = new FamilyBuilder();
        default:
            mealBuilder = new CustomBuilder();
    }

    mealBuilder.setMainItem(Food);
    ....

    return mealBuilder.getMeal().getPrice();
}