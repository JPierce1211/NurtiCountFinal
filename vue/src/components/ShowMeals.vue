<template>
  <div>
    <div class="MealForm">
      <form @submit.prevent="createMealForm">
        <div>
          <label for="meal-Name"> Meal Name: </label>
          <input type="text" v-model="meal.mealName" />
        </div>
        <div>
          <label for="meal-type">Meal Type </label>
          <select id="mealDropDown" v-model="meal.mealType">
            <option value="breakfast">Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Dinner">Dinner</option>
            <option value="Snack">Snack</option>
          </select>
        </div>
        <div>
          <label for="date"> Date: </label>
          <input type="date" id="logDay" v-model="meal.logDay" />
        </div>
        <div>
          <label for="meal-description"> Meal Description: </label>
          <textarea
            v-model="meal.mealDescription"
            placeholder="Write a description for Your Meal"
          ></textarea>
        </div>
        <div>
          <button type="submit">Create Meal</button>
          <!-- <button @click="$router.push('component/FoodDetails.vue')"> Add Food to Meal </button> -->
        </div>
      </form>
    </div>

    <div>
      <table>
        <thead>
          <tr>
            <td>List of All Meals</td>
            <td>Description Of All Meals</td>
          </tr>
        </thead>

        <tbody>
          <tr v-for="meal in mealsArray" :key="meal.mealId">
            <td>{{ meal.mealName }}</td>
            <td>{{ meal.mealDescription }}</td>
            <td>
              <button @click="editMeal(meal)">Meal Details</button>
              <button @click="addFood()">Add Food</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
//import addFood from "../component/Meals.vue";

export default {
  data() {
    return {
      meal: {
        mealId: 0,
        userId: 0,
        mealType: "",
        mealName: "",
        mealDescription: "",
        logDay: "",
        isQuickMeal: false,
      },

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

  methods: {
    createMealForm() {
      console.log("Creating meal with:", this.meal);
      FoodService.createMeal(this.meal)
        .then((response) => {
          console.log("Response body: ", response.data);
          this.mealsArray.push(response.data);
          this.meal = {
            mealId: 0,
            userId: 0,
            mealType: "",
            mealName: "",
            mealDescription: "",
            logDay: "",
            isQuickMeal: false,
          };
          let increment = 1;
          this.$store.commit('SET_STAR_RATING', increment);
          console.log("Star Rating: ", this.$store.state.starRating );
        })
        .catch((error) => {
          console.error("Error creating the meal:", error);
        });
    },

      editMeal(meal) {
    this.$router.push({name:"mealDetails", params: {mealId: meal.mealId}})
    },

    addFood() {
    this.$router.push({ name: "foodDetails" });
    },
  },
};
</script>

<style>
</style>