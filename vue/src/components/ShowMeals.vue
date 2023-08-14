<template>
    <div>
        <div class="MealForm">
            <form @submit.prevent="createMealForm">
                <div>
                    <label for="meal-Name"> Meal Name: </label>
                    <input type="text" v-model="meal.name"/>
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
                    <textarea v-model="meal.description" placeholder="Write a description for Your Meal" ></textarea>
                </div>
                <div>
                    <button type="submit">Create Meal</button>
                    <button @click="$router.push('component/Meals.vue')"> Add Food to Meal </button>
                </div>  
            </form>                    
        </div>
        <div >
            <table>
                <thead>
                    <tr> 
                        <td>List of All Meals </td>
                        <td>Description Of All Meals</td>
                        <td></td>
                    </tr>  
                </thead> 
                <tbody>
                    <tr v-for="meal in meals" :key="meal.id">
                        <td>{{meal.name}}</td>
                        <td>{{meal.description}}</td>
                        <td>
                            <button @click="editMeal(meal)">Edit Meal</button>
                            <button @click="addFood()"> Add Food to Meal </button>
                        </td>    
                    </tr>        
                </tbody>     
            </table>   
        </div>       
    </div>   
</template>

<script>
//import addFood from "../component/Meals.vue";

export default {


    data() {
        return{

            meal:{
                mealId: "",
                name:"",
                type:"",
                description:"",
                isQuickMeal:"",
                logDay:"",

            },

            meals: [],
        };
    },

    methods: {
        createMealForm(){
           this.meal = {
                name: '',
                mealType: '',
                logDay: '',
                description: '',
            };

            this.meal.push(this.meal);
        },

        // editMeal(meal){

        //  }
    }

}
</script>

<style>

</style>