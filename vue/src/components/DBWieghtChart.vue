<template>
  <div>
    <div class="db-weight-chart">
          <Progress />
    </div>
    <div class="db-weight-log">
      <!-- Button to take you to the progress page and quickly input most recent weight info -->
      <p class="nav-item"> Track Progress </p>
    </div>
  </div>
</template>

<script>
import Progress from '../components/Progress.vue'
export default 
{
  name: "progress",
  
  components: 
  {
      Progress
  }
};
</script>

<style scoped>
/* Add your page-specific styles here */
</style>
