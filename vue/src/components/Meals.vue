<template>
    <div>
        <h1 id="title">My Meals</h1>
        <div class="main">
            <div id="date">
                <label for="logDay">Date:</label>
                <input type="date" id="logDay" v-model="meal.logDay" >
               {{format_date(meal.logDay)}}
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
            </div>
            <div>  
                
            </div>    


        </div> 
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