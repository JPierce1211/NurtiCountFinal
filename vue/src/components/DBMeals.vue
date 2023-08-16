<template>
  <div class="db-meals">
        <h3>Recent Quick Meals</h3>
        <ul>
            <li v-for="meal in quickMeals" :key="meal.Id">
                {{meal.mealName}} - {{meal.description}}
            </li>
        </ul>
</div>
</template>

<script>
import FoodService from "../services/FoodService";

export default {
    data(){
        return {
            mealsArray: [],
        };
    },

    mounted(){
        FoodService.getAllMeals()
        .then((response) => {
            this.mealsArray = response.data;
            console.log("All of the meals: ", this.mealsArray);
        })
        .catch((error) => {
            console.error("Error fetching the meals:", error)
        })
    },

    computed: {
        quickMeals(){
            return this.mealsArray
                .filter(meal => meal.quickMeal == true)
                //.sort((a,b) => new Date(b.logDay) - new Date(a.logDay))
                // .slice(-3);
        }
    }
}
</script>

<style>

</style>

