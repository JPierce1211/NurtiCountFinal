<template>
  <div>
      <h2 id="title">Edit Meal</h2>
      <div class="editMealForm">
           <form @submit.prevent="updateMeal">
        <div class="input">
          <label for="meal-Name"> Meal Name </label>
          <input type="text" id="meal-name" v-model="meal.mealName" />
        </div>
        <div>
          <label for="meal-type">Meal Type </label>
          <select id="mealDropDown" v-model="meal.mealType">
            <option value="breakfast">Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Dinner">Dinner</option>
            <option value="Snack">Snack</option>
          </select>
        <div>
          <label for="quick meal">Quick Meal </label>
          <input type="checkbox" id="quick meal" v-model="meal.isQuickMeal" />
        </div>
        </div>
        <div class="input">
          <label for="date"> Date </label>
          <input type="date" id="logDay" v-model="meal.logDay" />
        </div>
        <div class="text-description">
          <label for="meal-description"> Meal Description: </label>
          <textarea
            v-model="meal.mealDescription"
            placeholder="Write a description for Your Meal"
            id="description"
          ></textarea>
        </div>
        <div>
          <button type="submit">Update Meal</button>
        </div>
      </form>
      </div>    
  </div>    
</template>

<script>
import FoodService from '../services/FoodService'

export default {
    data(){
        return {
                meal:{
                    meal: {
                    mealId: 0,
                    userId: 0,    
                    mealType: "",
                    mealName: "",
                    mealDescription: "",
                    logDay: "",
                    isQuickMeal: false,
                },
            }
        };
    },

    created() {
        const mealId = this.$route.params.mealId;
        // this.getMealDetails(mealId);
        // this.getFoodsDetails(mealId);
        FoodService.getMealDetails(mealId)
            .then((response) => {
            this.meal = response.data;
        })
            .catch((error) => {
            console.error("Error fetching the meal details:", error);
        });
    },
    
    methods: {
        updateMeal(){
              console.log("updateMeal method called");
              console.log("Sending meal data:", this.meal);
            FoodService.updateMeal(this.meal.mealId, this.meal)
            .then((response) =>{
                console.log("response from server:", response);
                this.$router.push({name:'mealDetails'});
            })
            .catch(error => {
                console.error("Error updating the meal:", error);
            });
        }
    }
}
</script>

<style>

</style>