<template>
    <div>

        <DBProfile />

        <router-link class="nav-item" v-bind:to="{ name: 'createProfile' }"> Edit Profile </router-link>

    </div>
</template>

<script>

import DBProfile from './DBProfile.vue';

export default {

    components: 
    {
      DBProfile,

    }
}

</script>


<style scoped>



</style>