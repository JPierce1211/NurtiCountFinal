<template>
  <div class="form">
    <form @submit.prevent="submitWeight">
      <label for="currentWeight">Enter your current weight (lbs):</label>
      <input v-model="currentWeight" class="text-box" type="number" id="currentWeight" required>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import ProgressServices from '../services/ProgressService';

export default {
  data() 
    {
        return {
        currentWeight: ''
        };
    },
  methods: 
    {
        async submitWeight() 
            {
            try 
                {
                    const heightInInches = this.$store.state.profile.height;
                    const weightInPounds = parseFloat(this.currentWeight);
                    const bmi = (weightInPounds / (heightInInches * heightInInches)) * 703;

                    const Roundedbmi = Number(bmi.toFixed(2));

                    const newGoals = 
                        {
                            userId: this.$store.state.profile.userId,
                            desiredWeight: weightInPounds,
                            bmi: Roundedbmi,
                            logDay: new Date().toISOString().substr(0, 10)
                        };

                    const response = await ProgressServices.createGoal(newGoals);
                    console.log('New goal created:', response.data);
                } 
            catch (error) 
                {
                    console.error('Error creating goal:', error);
                }
            }
    }
};
</script>

<style scoped>

.form
{
    display: flex;

    justify-content: space-evenly;
    margin: auto;
}

</style>
