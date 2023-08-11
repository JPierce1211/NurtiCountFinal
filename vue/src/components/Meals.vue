<template>
    <div>
        <h1 id="title">My Meals</h1>
        <div class="main">
            <div id="date">
                {{format_date(meal.logDay)}}
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
            meal:{
                mealId: '',
                mealType:'',
                logDay:'',
                isQuickMeal:'',
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