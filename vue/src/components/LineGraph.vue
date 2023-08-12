<template>
  <div>
    <canvas ref="lineChart"></canvas>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    profileId: Number, // Pass the user ID from your Vue store
  },
  data() {
    return {
      progressData: [],
    };
  },
  async mounted() {
    await this.fetchProgressData();
    this.createLineChart();
  },
  methods: {
    async fetchProgressData() {
      try {
        const response = await axios.get(`/progress/${this.profileId}`);
        this.progressData = response.data;
      } catch (error) {
        console.error('Error fetching progress data:', error);
      }
    },
    createLineChart() {
      const labels = this.progressData.map(entry => entry.log_day);
      const weights = this.progressData.map(entry => entry.current_weight);

      const ctx = this.$refs.lineChart.getContext('2d');
      new window.Chart(ctx, {
        type: 'line',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Current Weight',
              data: weights,
              borderColor: 'rgba(75, 192, 192, 1)',
              fill: false,
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
        },
      });
    },
  },
};
</script>

<style scoped>
/* Add your component-specific styles here */
</style>
