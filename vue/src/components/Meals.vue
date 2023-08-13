<!-- Change the componenet to meal search -->
<!-- this component will be where they seach for meals and then be taken to another component to see all the meals they've chosen -->
<template> 
  <div>
    <h1 id="title">My Meals</h1>
    <div class="main">
      <table id="tblFood">
        <thead id="tblhead">
          <tr>
            <th>Date</th>
            <th>Meal Type</th>
            <th>Number Of Servings</th>
            <!-- test if our database can search multiple strings  -->
            <th>Food Search</th>
            <th>Search Food</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input type="date" id="logDay" v-model="meal.logDay" />
              {{ format_date(meal.logDay) }}
            </td>
            <td>
              <select id="mealDropDown" v-model="meal.mealType">
                <option value="breakfast">Breakfast</option>
                <option value="Lunch">Lunch</option>
                <option value="Dinner">Dinner</option>
                <option value="Snack">Snack</option>
              </select>
            </td>
            <td>
              <input
                type="text"
                id="numOfServings"
                v-model="food.numOfServings"
                min="1"
              />
            </td>
            <td>
              <label for="foodFilter">Enter a specific food name, including cooking style:</label>
              <input type="text" id="foodFilter" v-model="search.foodName" placeholder="e.g Grilled Chicken Breast"/>
            </td>
            <!-- <td>
                   <select id="quickMealFilter" v-model="search.isQuickMeal"/>
                        <option value="false">Show All</option>
                        <option value="true">Quick Meal</option>
               </td> -->
            <td>
              <!-- when this button is hit then it should be add to the meal's array/table -->
              <!-- need a is loading method might take this out -->
              <button v-on:click="showTable = !showTable" :disabled="!isTableFilled">Search Food</button>
            </td>
          </tr>
        </tbody>
      </table> 
      <!-- add button to move to see all meals for the day -->
      <!-- create a show method -->
      <table
        v-show="showTable"
        v-on:submit.prevent="addFood"
        id="tblFoodResults"
      >
        <thead>
          <tr>
            <th>Food Name</th>
            <th>Food Type</th>
            <th>Serving Size</th>
            <th>Calories</th>
            <th>Quick Meal?</th>
            <th>Add Food to Meal</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <!-- will need a v-for to show multiple results if going down that route -->
            <td>{{ food.foodName }}</td>
            <td>{{ food.foodType }}</td>
            <td>{{ food.servingSize }}</td>
            <td>{{ food.calories }}</td>
            <td>
              <!-- going to need a method to turn isQuickMeal True -->
              <input
                type="checkbox"
                v-model="meal.isQuickMeal"
                v-on:click.prevent="addQuickMeal"
              />
            </td>
            <td>
              <!-- button should be disabled if there is no foods selected -->
              <!-- the meal method should save meals to an array-->
              <button v-bind:disabled="!selectedFood.length" v-on:click ="addMeal()"> Add Meal</button>
            </td> 
          </tr>
        </tbody>
      </table>
    </div>
    <div class="butn-ShowMeals">  
      <!-- this button will save the foods into a meal and transfer it to the next componenet -->
      <button @click="$router.push('component/showMeals.vue')">Show Meals</button>
      <button v-on:click="newSearch"> New Search </button>
    </div> 
  </div>  
</template>

<script>
import moment from "moment";
import profileService from "../services/ProfileService"
export default {
  data() {
    return {
      selectedFood: [],

      showTable: false,

      tableFilledError: "",

      meal: {
        mealId: "",
        mealType: "",
        logDay: "",
        isQuickMeal: "",
      },
      food: {
        foodId: "",
        foodName: "",
        foodType: "",
        servingSize: "",
        calories: "",
        numOfServings: "",
      },
      search: {
        foodName: "",
        isQuickMeal: "false",
      },
    };
  },

  methods: {
    //  got from stackoverflow
    format_date(value) {
      if (value) {
        return moment(value).format("YYYYMMDD");
      }
    },

    // triggers the food search
    searchFood(){
      let searchName = this.search.foodName;
      //this method needs to get made in service to a Get method
      if (!this.checkingTable()){
          return;
      }
      profileService.getSearchedMeals(searchName)
      .then(response => {
        this.selectedFood = response.data;
        this.showTable = true;
      })
      .catch(error => {
        console.error(error);
      });
    },

    checkingTable(){
      if (!this.meal.logDay|| !this.food.numOfServings || !this.search.foodName) {
        this.tableFilledError = "All fields must be filled out.";
        return false;
      }
        this.formError = "";
        return true;
    },

    // Adds the selected food item to selectedFood array
    addFood(){
      
    },

    // toggles the quick meal option for the selected food or meal
    addQuickMeal(){

    },

    //takes selected food items and puts it in a meal object
    addMeal(){

    },

    //Resets the search input
    newSearch(){

    },


  },

  computed: {
  isTableFilled(){
    return this.search.foodName && this.meal.logDay && this.food.numOfServings;
  }

}
};


</script>

<style>
</style>