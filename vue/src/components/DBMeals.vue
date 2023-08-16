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
        })
        .catch((error) => {
            console.error("Error fetching the meals:", error)
        })
    },

    computed: {
        quickMeals(){
            return this.mealsArray
                .filter(meal => meal.isQuickMeal = 'True')
                //.sort((a,b) => new Date(b.logDay) - new Date(a.logDay))
                .slice(0,3);
        }
    }
}
</script>

<style>

</style>

