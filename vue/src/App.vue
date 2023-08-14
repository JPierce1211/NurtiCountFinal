<template>
  <div id="app" class="container">
    <!--     
      <div class="logo">
        <span>Logo Goes Here</span>
      </div> 
    -->

    <div class="banner">
      <img :src="require('@/imgs/pngs/NC_Logo_02.png')" />
    </div>
    <nav>
    <div id="nav" class="nav-menu">
      <router-link class="nav-item" v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">Home</router-link>&nbsp;
      <router-link class="nav-item" v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''">Profile</router-link>&nbsp;
      <router-link class="nav-item" v-bind:to="{ name: 'progess' }" v-if="$store.state.token != ''">Progress</router-link>&nbsp;
      <router-link class="nav-item" v-bind:to="{ name: 'showMeals' }" v-if="$store.state.token != ''">My Meals</router-link>&nbsp;
      <router-link class="nav-item" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;
      <router-link class="nav-item" v-bind:to="{ name: 'login' }" v-if="$store.state.token === ''">Login &#x2022; Sign Up</router-link>
      
    </div>
    </nav>
    <div class="content">
      <router-view />
    </div>    
  </div>
</template>
 <style scoped>
 @import "style/feel.css";
 </style>