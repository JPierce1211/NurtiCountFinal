<template>
  <form v-on:submit.prevent="submitForm" class="profileForm">
      <div>
          <label for="height">Height:</label>
          <input id="height" type="text" class="userInput" v-model=""
      </div> 

</template>

<script>
export default {
    name: "profile-form",
    data(){
        return {
            profile: "",
             id:'',
            userId:'',
            height:'',
            display_name:'',
            birthday:'',
            picture:'',
            weight:'',
        }
    }
}
</script>

<style>

</style>