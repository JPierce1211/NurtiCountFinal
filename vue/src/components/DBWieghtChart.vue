<template>
  <div>
    <div class="db-weight-chart">
      <line-graph :profile-id="profileId"></line-graph>
    </div>
    <div class="db-weight-log">
      <!-- Button to take you to the progress page and quickly input most recent weight info -->
      <p class="nav-item"> Track Progress </p>
    </div>
  </div>
</template>

<script>
import LineGraph from './LineGraph.vue';

export default {
  components: {
    LineGraph,
  },
  data() {
    return {
      profileId: 3,
    };
  },
};
</script>

<style scoped>
/* Add your page-specific styles here */
</style>
