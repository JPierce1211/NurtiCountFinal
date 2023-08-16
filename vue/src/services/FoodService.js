import axios from "axios";

export default {
    getFoodByName(searchName)
    {
        const food = "?foodByName=" + searchName;
       const url = `meals/food/foodByName/${food}`;
       return axios.get(url)
    },

    superSearch(searchName)
    {
       const search = "?foodByName=" + searchName;
       const url = `meals/food/supersearch${search}`;
       return axios.get(url)
    },

    addToFoods(foodItem){//Added 'saveNewFood' method from the FoodController
        return axios.post('/meals/food', foodItem)    
    },

    addFoodToMeal(meal){//Added 'addFoodToMeal' in the MealsController
        return axios.post(`meals/${meal.id}/addFood`, meal)
    },

    createMeal(meal){
        alert("create meal")
        return axios.post('/createMeal',meal);
    },

    getMealDetails(mealId){
        return axios.get('/meals/' + mealId);
    },

    getAllMeals(){
        return axios.get("/meals");
    },

    deleteFoodById(foodId){
        return axios.delete('/meals/food/' + foodId)
    }


}