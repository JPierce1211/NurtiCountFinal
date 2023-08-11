<template>
    <div>
        <h1 id="title">My Meals</h1>
        <div class="main">
        <table id="tblFood">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Meal Type</th>
                    <th>Number Of Servings</th>
                    <th>Food Search</th>
                    <th>Quick Meal</th>
                </tr>   
            </thead>    
            <tbody>
            <tr>
                <td>
                <input type="date" id="logDay" v-model="meal.logDay" >
<<<<<<< HEAD

            </div>
            <div>
            </div>
            <div id="mealType">
              <label for="mealType">Meal Type:</label>
              <select id="mealDropDown" v-model="meal.mealType">
                <option value="breakfast">Breakfast</option>
                <option value="Lunch">Lunch</option>
                <option value="Dinner">Dinner</option>
                <option value="Snack">Snack</option>
              </select>
            </div>
            <div>  
                
            </div>    
=======
               {{format_date(meal.logDay)}}
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
                   <input type="text" id="numOfServings" v-model="food.numOfServings"/>
               </td>   
               <td>
                   <input type="text" id="foodFilter" v-model="search.foodName"/>
               </td>  
               <td>
                   <select id="quickMealFilter" v-model="search.isQuickMeal"/>
                        <option value="false">Show All</option>
                        <option value="true">Quick Meal</option>
               </td>
               <td>
                   <!-- when this button is hit then it should be add to the meal's array/table -->
                   <button v-on:click = "showTable =! showTable">Add Food To Meal</button>
               </td>             
            </tr> 
        </table>   
        <!-- create a show method -->
        <table v-show="showTable" v-on:submit.prevent="addFood" id="tblMeal">
            <thead>
                <tr>
                    <th>Food Name</th>
                    <th>Food Type</th>
                    <th>Serving Size</th>
                    <th>Calories</th>
                    <th>Quick Meal?</th>
                    <th>Add Meal</th>
                </tr>
                <tr>
                    <td>{{food.foodName}}</td>
                    <td>{{food.foodType}}</td>
                    <td>{{food.servingSize}}</td>
                    <td>{{food.calories}}</td>
                    <td>
                        <!-- going to need a method to turn isQuickMeal True -->
                        <input type="checkbox" v-model="meal.isQuickMeal" v-on:click.prevent="addQuickMeal">
                    <td>     
>>>>>>> 6fa2c738004fb38de6560f4f70b5510923003716

                </tr>        
            </thead>    
        </table>        
    </div>  
</template>

<script>
import moment from 'moment'
export default {
    data(){
        return{
            selectedFood: [],

            meal:{
                mealId: '',
                mealType:'',
                logDay:'',
                isQuickMeal:'',
            },
            food:{
                foodId:'',
                foodName:'',
                foodType:'',
                servingSize:'',
                calories:'',
                numOfServings: '',
            },
            search:{
                foodName:'',
                isQuickMeal: 'false'
            }
        }
    },

     methods: { 
        //  got from stackoverflow
      format_date(value){
         if (value) {
           return moment(value).format('YYYYMMDD')
          }
      },
   },
}
</script>

<style>

</style>