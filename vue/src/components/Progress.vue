<template>
  <div class="prog-content">
      <div>
          <!-- Bar Chart -->
          <DBWieghtChart />
      </div>
      <div>
          <!-- Form -->
          <progress-form />
      </div>
    <button @click="fetchMyGoals" class="center-content">See All Entries</button>
    <div v-for="(goal, index) in goals" :key="index">
        <p class="prog-label">
          Date (Year-Month-Day)
        </p> 
        <p class="prog-value">
            {{ goal.logDay }}
        </p>
        <p class="prog-label">
            Weight (lbs)
        </p> 
        <p class="prog-value">
            {{ goal.desiredWeight }}
        </p>
        <p class="prog-label">
            BMI index
        </p> 
        <p class="prog-value">
            {{ goal.bmi }}
        </p>
      
      <hr>
    </div>  
  </div>
</template>

<script>
import ProgressServices from '../services/ProgressService';
import DBWieghtChart from './DBWieghtChart.vue';
import ProgressForm from './ProgressForm.vue';


export default {
    name: 'Progress',

    components:
        {
            DBWieghtChart,
            ProgressForm,
        },

    data() {
        return {
        goals: [] // Store the fetched goals here
        };
    },
    methods: 
        {
            async fetchMyGoals() 
                {
                try 
                    {
                        const response = await ProgressServices.getMyGoals();
                        this.goals = response.data;
                        console.log(this.goals);

                    }
                        catch (error) 
                    {
                        console.error(error);
                    }
                }
            }
};
</script>



<style scoped>

.prog-content
{
    display: flex;
    flex-direction: column;
    justify-items: center;
    justify-content: space-between;
    gap: 40px;
    margin: 40px;
}

.prog-label 
{
    margin-left: 100px;
    font-size: 10pt; 
    font-style: italic;
    color: #5b695d;
}

.prog-value 
{
    margin-left: 400px; 
}
</style>








