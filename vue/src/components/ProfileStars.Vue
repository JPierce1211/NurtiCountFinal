<template>
  <div>
    <h2><!--Title For Stars Here--></h2>
    <ul>
      <li v-for="(meal, index) in meals" :key="index">
        <h3>Meal ID: {{ meal.mealId }}</h3>
        <p>User ID: {{ meal.userId }}</p>
        <p>Meal Type: {{ meal.mealType }}</p>
        <p>Meal Name: {{ meal.mealName || 'No name available' }}</p>
        <p>Meal Description: {{ meal.mealDescription || 'No description available' }}</p>
        <p>Log Day: {{ meal.logDay }}</p>
        <!--<p>Total Calories: {{ meal.totalCalories }}</p>-->
        <p>Quick Meal: {{ meal.quickMeal ? 'Yes' : 'No' }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import mealService from "../services/MealService.js";

export default 
{
  data() 
  {
    return {
      meals: []
    };
  },
  mounted() 
  {
    // const response = mealService.getMeal();

    // if(response && response.data)
    //   {
    //     this.meal = response.data; 
    //   }
    // else
    //   {
    //     console.log("Error fetching meals:");
    //   }

      mealService.getMeals()
        .then(response => {
          this.meals = response.data;
          console.log('here is the meals object' + this.meals);
        })
        .catch(error => {
          console.log("Error fetching meals:", error);
        });
  }
};
</script>

<style scoped>

    

</style>