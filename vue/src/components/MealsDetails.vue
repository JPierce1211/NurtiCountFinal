<template>
  <div>
      <div class="Meal-details">
        <h1 class="title"> Meal Details </h1>
        <p class="meal-name"> Meal Name: {{meal.name}}</p>
        <p class="meal-type"> Meal Type: {{meal.type}} </p>
        <p class="meal-date"> Date: {{meal.logDay}} </p>
        <p class="meal-description"> Description: {{meal.description}} </p>
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
export default {
    data(){
        return {
            meal: {},

            foods:[],
        }
    },

    created(){
        const mealId = this.$route.params.mealId;
        this.getMealDetails(mealId);
        this.getFoodsDetails(mealId);
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