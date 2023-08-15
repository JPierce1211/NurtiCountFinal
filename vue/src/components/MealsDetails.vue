<template>
  <div>
      <div class="Meal-details">
        <h1 class="title"> Meal Details </h1>
        <p class="meal-name"> Meal Name: {{mealDetails.mealName}}</p>
        <p class="meal-type"> Meal Type: {{mealDetails.mealType}} </p>
        <p class="meal-date"> Date: {{mealDetails.logDay}} </p>
        <p class="meal-description"> Description: {{mealDetails.mealDescription}} </p>
      </div>
      <div class="food-details">
          <h2 class="food-title">Foods</h2>
          <div v-for="food in foods" :key="food.id">
                <p class="food-name"> Food Name: {{food.name}}</p>
                <p class="food-type"> Food Type: {{food.type}} </p>
                <p class="food-servingSize"> Serving Size: {{food.servingSize}} </p>
                <p class="food-numOfCalories"> Number Of Calories: {{food.calories}} </p>
          </div>
      </div>    
  </div>    
</template>

<script>
import FoodService from '../services/FoodService';
export default {
    data(){
        return {
            mealDetails: {},

            foods:[],
        }
    },

    created(){
        const mealId = this.$route.params.mealId;
        // this.getMealDetails(mealId);
        // this.getFoodsDetails(mealId);
        FoodService.getMealDetails(mealId)
        .then(response => {
            this.mealDetails = response.data;
        })
        .catch(error => {
            console.error("Error fetching the meal details:", error);
        })
    },

    methods: {
        // getMealDetails(mealId){

        // },

        // getFoodsDetails(mealId){

        // }
    }
}
</script>

<style>

</style>