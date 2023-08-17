<template>
  <div>
    <div class="MealForm">
      <form @submit.prevent="createMealForm">
        <div class="input">
          <label for="meal-Name"> Meal Name </label>
          <input type="text" id="meal-name" v-model="meal.mealName" />
        </div>
        <div>
          <label for="meal-type">Meal Type </label>
          <select id="mealDropDown" v-model="meal.mealType">
            <option value="Breakfast">Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Dinner">Dinner</option>
            <option value="Snack">Snack</option>
          </select>
        <div>
          <label for="quick meal">Quick Meal </label>
          <input type="checkbox" id="quick meal" v-model="meal.quickMeal" @change="makeQuickMeal"/>
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
          <button type="submit">Create Meal</button>
          <p class="tblfill-error">{{tableFilledError}}</p>
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
              <button @click="addFood(meal)">Add Food</button>
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
        quickMeal: false,
      },
      mealsArray: [],

      tableFilledError: "",
      
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

      FoodService.createMeal(this.meal)
        .then((response) => {
          this.mealsArray.push(response.data);
          this.meal = {
            mealId: 0,
            userId: 0,
            mealType: "",
            mealName: "",
            mealDescription: "",
            logDay: "",
            quickMeal: false,
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

    addFood(meal) {
      console.log("Meal Id is: ", meal.mealId);
    this.$router.push({ name: "foodDetails", params: {mealId: meal.mealId} });
    },
  },
    makeQuickMeal() {
     this.meal.quickMeal = !this.meal.quickMeal;
      // this.meal.quickMeal = true;
    },

    checkingTableForm() {
      if (!this.meal) {
        this.tableFilledError = "Please fill out form"; //this error is not working (It works for me. - Juan)
        return false;
      }
      this.tableFilledError = "";
      return true;
    },

    computed: {
    isTableFilled() {
      //Inital method was this ---> return this.search.foodName && this.meal.logDay && this.food.numOfServings && this.meal.mealType
      return this.search.foodName;
    },
  }

};


</script>
<style>
.MealForm {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 10px;
  margin-right: 20px;
}
.input{
  display: flex;
  align-items: flex-start;
  margin-right: 20px;
}
.text-description{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
textarea{
  width:100%;
  resize: vertical;
}

</style>






