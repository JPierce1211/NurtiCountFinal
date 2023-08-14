<template>
  <div>
    <!-- Stars go here -->

    <!-- <p>Number of Stars: {{ numOfStars }}</p> -->
    <div class="stars-container">
      <!-- <img
        v-if="numOfStars <= 5"
        v-for="index in numOfStars"
        :key="index"
        src="../imgs/pngs/star.jpg"
        alt="Star"
        class="star-image"
      />
      <img
        v-else
        src="../imgs/pngs/star.jpg"
        alt="Star"
        class="star-image"
      /> -->
      <p v-if="numOfStars > 5" class="star-number text-font">{{ numOfStars }}</p>
    </div>
  </div>
</template>

<script>
import mealService from "../services/MealService.js";

export default 
{

    data() 
        {
            return {
                    meals: [], 
                    numOfStars: 0,
                };
        },
    async mounted() 
        {
            await this.loadMeals();
            await this.calculateNumOfStars();
        },
    methods: 
        {
            async loadMeals() 
                {
                    try 
                        {
                            const response = await mealService.getMeals();
                            this.meals = response.data;


                            /// delete the code below before deployment
                            response.data.forEach((object, index) => 
                            {
                            console.log(`Object ${index + 1}:`, object);
                            });

                        } 
                    catch (error) 
                        {
                            console.error('Error fetching meals:', error);
                        }
                },
            async calculateNumOfStars() 
                {
                if (this.meals.length === 0) 
                    {
                        this.numOfStars = 0;
                        return;
                    }

                let currentDay = this.meals[0].logDay;
                let streak = 1;

                for (let i = 1; i < this.meals.length; i++) 
                    {
                            const nextDay = this.meals[i].logDay;
                            const currentDate = new Date(currentDay);
                            currentDate.setDate(currentDate.getDate() + 1);
                            const nextDate = new Date(nextDay);

                            if (currentDate.toDateString() === nextDate.toDateString()) 
                                {
                                    streak++;
                                } 
                                    else 
                                {
                                    break;
                                }

                            currentDay = nextDay;
                    }

                    this.numOfStars = streak - 1;
                    console.log(this.numOfStars + ' Star User!!');
                },
        }
}
</script>

<style>
.star-badge {
 
  font-size: 24px;
  margin-right: 5px;
}
</style> 

<style scoped>
.stars-container 
{
  display: flex;
  align-items: center;
  gap: 10px;
}

.star-image 
{
  width: 20px;
  height: 20px;
}

.star-number
{
  position: relative;
  bottom: -10px;
  left: -10px;
  font-size: 14px;
  font-weight: bold;
}

</style>