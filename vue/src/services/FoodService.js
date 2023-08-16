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

    addFoodToMeal(foodmealdto){//Added 'addFoodToMeal' in the MealsController
        return axios.post(`meals/${foodmealdto.mealId}/addFood`, foodmealdto)
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

    //getfoodbyId

    deleteFoodById(foodId){
        return axios.delete('/meals/food/' + foodId)
    },

    updateMeal(mealId){
        return axios.put(`/meals/` + mealId)
    }


}