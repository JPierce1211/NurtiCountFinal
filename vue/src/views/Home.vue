<template>
  <div>
<!-- <div class="home"> -->
  <header>
      <div class="header">
        <h1 id="logo">Logo Goes Here</h1>
        <h2 id="banner">Banner Goes Here</h2>
      </div>
  </header>
  <div class="nav-menu"> 
    <!-- need to add a v-bind? or a way to link the navs -->
    <nav>
      <button id="home">Home</button>
      <button id="add-meals">My Meals</button>
      <button id="quick-meals">Quick Meals</button>
      <button id="profile">My Profile</button>
      <button id="progress">My Progress</button>
    </nav>
  </div>
  <div class="main">
    <div class="body-stars">
      <!-- Are we also going to add a link to the meals screen -->
      <h1 id="star-status">Current Streak</h1>
      <h2>Some info about consecutive meals</h2>
    </div>
    <div class="body-health">
      <h1 id="health-info">Current Health Info<h1>
       <h2>Some info about current health</h2> 
    </div>
    <div>
      <h1>Last Meal Logged:</h1>
      <h2>Date of the last time food was logged<h2>
    </div> 

    </div>

  </div>
    <!-- <h1>Home</h1> -->
    <!-- <p>You must be authenticated to see this</p> -->
  <!-- </div> -->
</template>

<script>
export default {
  name: "home"
};
</script>
