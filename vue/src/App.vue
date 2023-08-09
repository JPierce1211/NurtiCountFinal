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

    <div id="nav" class="nav-menu">
      <router-link class="nav-item" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&#x2022;&nbsp;
      <router-link class="nav-item" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>

    <div class="content">
      <router-view />
    </div>
    
  </div>
</template>
 <style>
 @import "style/feel.css";
 </style>
