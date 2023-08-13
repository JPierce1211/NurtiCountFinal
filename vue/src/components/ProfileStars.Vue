<template>
    <div>
        <p>Stars go here</p>
        <form @submit.prevent="findMealTest">
            <button class="login-button" type="submit">Find Meals</button>
        </form>

    </div>
</template>

<script>
import mealService from "../services/MealService";

export default
    {
        name: 'profileStars',

   
    

    methods:

        {
            findMealTest() 
                {
                    mealService.getMeals(this.$store.state.profile.userId)
                        .then(response => {

                            alert(response.status);
                            
                        if (response.data) 
                            {
                                
                                console.log(response.data);

                            } 
                        else 
                            {
                                console.log('no response from meals table in database');
                            }
                            })
                        .catch(error => 
                            {
                            console.error('Error finding meals in existence:', error);
                            });
                }
            
        }
    }


</script>