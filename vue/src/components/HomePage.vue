<template>
  <div>
      <div class="dashboard">

        <div class="dynamic-profile">
          <!-- Congrats!! You are a ___ Star user! Show off to your friends -->

          <DBProfile />

          <!-- Insert Dynamic content (Profile Pic and # of stars) that leads to the profile endpoint -->

        </div>

        <div class="dynamic-meals">

          <DBMeals />
          <!-- Insert Dynamic content (suggested meals or quick foods and meals) that leads to the my meals endpoint -->

        </div>

        <div class="dynamic-progress">

          <DBWieghtChart />
          <!-- Insert Dynamic content (bar chart of pounds lost) that leads to the progress endpoint -->

        </div>


      </div>
    <div>
      <footer>Copyright Information 2023</footer>
    </div> 

  </div> 

</template>

<script>
import DBMeals from './DBMeals.vue';
import DBProfile from './DBProfile.vue';
import DBWieghtChart from './DBWieghtChart.vue';

export default {
    name: 'Home',

    components: 
    {
      DBProfile,
      DBMeals,
      DBWieghtChart,
    }
}


</script>

<style scoped>

.dashboard
{
    margin: auto;
    display: grid;
    justify-items: stretch;
    /* align-items: center;   */
    grid-gap: 20px;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 1fr 1fr;
    grid-template-areas: 
        "profile meals"
        "chart chart";
}

.dynamic-profile
{
    padding: 50px;
    box-shadow: 3px 3px 5px #5b695d;
    grid-area: profile;
    /* grid-column: 1; */
}

.dynamic-meals
{
    padding: 30px;
    box-shadow: 3px 3px 5px #5b695d;
    grid-area: meals;
    /* grid-column: 1; */

}

.dynamic-progress
{
    padding: 30px;
    box-shadow: 3px 3px 5px #5b695d;
    grid-area: chart;
    /* grid-column: 2;  */
}
</style>