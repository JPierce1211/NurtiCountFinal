<template>
  <div>
    <div class="line-chart" v-if="goals.length > 0">
      <div class="data-point" v-for="(goal, index) in goals" :key="index">
        <div class="bar" :style="{ height: (goal.desiredWeight / highestDesiredWeight) * 120 + 'px' }"></div>
        <div class="label">
          {{ goal.logDay }}
          <br>
          <br>
          {{ goal.desiredWeight }} lbs
        </div>
      </div>
    </div>
  </div>  
</template>

<style>

.line-chart 
{
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    height: 200px;
    border: 1px solid #ccc;
    padding: 10px;
}

.data-point 
{
    display: flex;
    flex-direction: column;
    align-items: center;
    width: calc(100% / 8);
}



.bar 
{
    background-color: #84a650;
    width: 20px;
    transition: height 3s, background-color 3s, width 3s ease;
}

.bar:hover
{
    background-color: #5b695d;
}

.label 
{
    margin-top: 5px;
    font-size: 12px;
    text-align: center;
}
</style>

<script>
import ProgressServices from '../services/ProgressService';

export default {
  data() {
    return {
      goals: [] // Store the fetched goals here
    };
  },

  computed: {
    highestDesiredWeight() {
      // Find the highest desiredWeight value among the goals
      return Math.max(...this.goals.map(goal => goal.desiredWeight));
    }
  },  

  mounted() {
    this.fetchMyGoals();
    this.subscribeToEvents();
  },
  methods: {
    async fetchMyGoals() {
      try {
        const response = await ProgressServices.getMyGoals();
        this.goals = response.data;

        // Sort goals by logDay in ascending order
        this.goals.sort((a, b) => a.logDay.localeCompare(b.logDay));

        // Display only the last 8 entries
        if (this.goals.length > 8) {
          this.goals = this.goals.slice(this.goals.length - 8);
        }
      } catch (error) {
        console.error(error);
      }
    },
    subscribeToEvents() {
      // Subscribe to the custom event to fetch goals again when needed
      this.$root.$on('fetch-goals', this.fetchMyGoals);
    }
  }
};
</script>
