<!-- Change the component to meal search -->
<!-- this component will be where they seach for meals and then be taken to another component to see all the meals they've chosen -->
<template> 
  <div>
    <!-- Juan's suggestion: I changed the title to 'My Foods'. Why? Because I said so! Sike, because this component is catered toward the food we're putting in the meal. 
    Don't like my idea? That's cool. Change it back: My Meals-->
    <h1 id="title">My Foods</h1>
    <div class="main">
      <table id="tblFood">
        <thead id="tblhead">
          <tr>
            <!-- <th>Date</th>
            <th>Meal Type</th> -->
            <th>Number Of Servings</th>
            <!-- test if our database can search multiple strings  -->
            <th>Food Search</th>
            <!-- <th>Search Food</th> -->
          </tr>
        </thead>
        <tbody>
          <tr>
            <!-- <td>
              <input type="date" id="logDay" v-model="meal.logDay" />
              
            </td>
            <td>
              <select id="mealDropDown" v-model="meal.mealType">
                <option value="breakfast">Breakfast</option>
                <option value="Lunch">Lunch</option>
                <option value="Dinner">Dinner</option>
                <option value="Snack">Snack</option>
              </select>
            </td> -->
            <td>
              <input
                type="text"
                id="numOfServings"
                v-model="food.numOfServings"
                min="1"
              />
            </td>
            <td>
              <label for="foodFilter">Enter a specific food name, including cooking style: </label>
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
              <p class="tblfill-error">{{tableFilledError}}</p>
              <!-- :disabled="!isTableFilled" -->
              <button v-on:click="searchFood" >Search Food</button>
            </td>
          </tr>
        </tbody>
      </table> 

      <!-- add button to move to see all meals for the day -->
      <!-- create a show method -->
      <p class="error"> {{ searchError }}</p>
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
            <th>Quick Food?</th>
            <th>Add Food to Meal</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="foodItem in selectedFood" :key="foodItem.foodId">
            <!-- will need a v-for to show multiple results if going down that route -->
            <td>{{ foodItem.foodName }}</td>
            <td>{{ foodItem.foodType }}</td>
            <td>{{ foodItem.servingSize }}</td>
            <td>{{ foodItem.calories }}</td>
            <td>
              <p class="error">{{isDuplicateError}}</p>
              <!-- going to need a method to turn isQuickMeal True -->
              <button v-on:click="addToQuickFoods(foodItem)">Add to Quick Foods</button>
            </td>
            <td>
              <!-- button should be disabled if there is no foods selected -->
              <!-- the meal method should save meals to an array-->
              <!-- v-bind:disabled="!selectedFood.length" -->
              <button v-on:click.prevent="saveFoods()">Add Food to Meal</button>
            </td> 
          </tr>
        </tbody>
      </table>
    </div>
    <div class="butn-ShowMeals">  
      <!-- this button will save the foods into a meal and transfer it to the next componenet -->
      <button @click="showMeal()">Show Meals</button>
      <button v-on:click="newSearch"> New Search </button>
    </div> 
  </div>  
</template>

<script>
import moment from "moment";
import foodService from "../services/FoodService"
import FoodService from '../services/FoodService';
export default {
  data() {
    return {
      selectedFood: [
      //    {
      //    foodId: "1",
      //   foodName: "Grilled Chicken Breast",
      //    foodType: "Protein",
      //    servingSize: "150g",
      //    calories: "165",
      //    numOfServings: "1",
      //  },
      //  {
      //     foodId: "2",
      //    foodName: "BBQ Chicken",
      //    foodType: "Protein",
      //   servingSize: "150g",
      //   calories: "165",
      //   numOfServings: "1",
      // }
      ],
      //change to false if not hardcoding
      showTable: false,

      tableFilledError: "",

      searchError: "",

      isDuplicateError: "",

      successMessage: "",

      quickFood: [],

      showForm: false,

      meal: {
        foods: [],
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
        isQuickFood: "false",
      },
      search: {
        foodName: "",
        //isQuickMeal: "false",
      },

      //  updatedFood: {
      //   foodId: "",
      //   foodName: "",
      //   foodType: "",
      //   servingSize: "",
      //   calories: "",
      //   numOfServings: "",
      //   isQuickFood: "false",
      // },
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
      let tableForm = this.checkingTableForm();
      //this is making sure all input is filled out
      if (!tableForm){
          return;
      } 

      this.searchError ="";

      foodService.getFoodByName(searchName)
      .then(response => {
        //this checks if there is no response
        if (response.data.length === 0){
          this.superSearch();
          this.searchError = "SuperSearch activated. (Calorie information based on 100 grams)";
          return;
        }
        this.selectedFood = response.data;
        this.showTable = true;
      })
      .catch(error => {
        if (error.status === 404){
          this.searchError = "No results found for this search.";
        } else {
          this.searchError = "An error happen while searching. Please try again.";
        }
        console.error(error);
      });
    },

    //The wonderful SuperSearch!
    superSearch(){
      let searchName = this.search.foodName;
      let tableForm = this.checkingTableForm();
      if (!tableForm){
          return;
      } 
      this.searchError ="";
      foodService.superSearch(searchName)
      .then(response => {
        if (response.data.length === 0){
          this.searchError = "Hmmm...this must be a new food we haven't heard about yet.";
          return;
        }
        this.selectedFood = response.data;
        this.showTable = true;
      })
      .catch(error => {
        if (error.status === 404){
          this.searchError = "No results found for this search.";
        } else {
          this.searchError = "An error happen while searching. Please try again.";
        }
        console.error(error);
      });
    },

    //Juan's suggestion: I bet you've noticed all the green in your code and may be wondering why. 
    //Food details page should be all about that: food. I took off the date and meal type too highlight the food search and info.
    //Why? The log day and meal type is already handled on the meals page. We don't need this info for the foods to add them to a meal.
    //If you don't like the suggestion, I understand. The original code is below to copy and paste back into the method.
    //if (!this.meal.logDay || !this.meal.mealType || !this.food.numOfServings || !this.search.foodName)

    checkingTableForm(){
      if (!this.food.numOfServings || !this.search.foodName) {
        this.tableFilledError = "All fields must be filled out."; //this error is not working (It works for me. - Juan)
        return false;
      }
        this.tableFilledError = "";
        return true;
    },

    // Adds the selected food item to selectedFood array
    //method not working
    addToQuickFoods(foodItem){
      //Checks the food id to see if its already saved
      let isDuplicate = false;
        for(let i = 0; i < this.quickFood.length; i++) {
          if (this.quickFood[i].foodId === foodItem.foodId){
            isDuplicate = true;
            break;
            //need a break to exit out the lopp if it finds true
          }
      }

      if (!isDuplicate){

      this.quickFood.push(foodItem);
      } else {
        this.isDuplicateError = "ERROR: Already added to Quick Foods. Please Make Another Selection.";
      }
    }, 

    saveFoods(){
      if(this.food.foodId === 0){
        FoodService.addToFoods(this.food).then(response => {
        if(response.status === 201){
          this.successMessage = "Added to your meal!"
          }
        })
      } 
    },

    //takes selected food items and puts it in a meal object
    addToFoodMeals(foodItem){
      this.updatedFood.push(foodItem);
    },

    //Resets the search input
    newSearch(){
        this.search.foodName = "";
        this.meal.logDay = "";
        this.meal.mealType = "";
        this.food.numOfServings = "";
        this.searchError = "";
        this.tableFilledError = "";
        this.showTable = false;
    },

    showMeal(){
      this.$router.push({name:'showMeals'});
    }


  },

  computed: {
    isTableFilled(){
      return this.search.foodName && this.meal.logDay && this.food.numOfServings && this.meal.mealType;
    }

  },

};


</script>

<style>

#title {
  text-align: center;
}

p.error {
  text-align: center;
  font-weight: bolder;
  font-style: italic;
}

</style>