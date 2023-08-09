
<template>
<!-- This v-bind makes sure the child is getting and updating information -->
  <edit-profile v-bind:userID="parseInt($route.params.userId)" />
</template>

<script>
import editProfile from '../components/EditProfile.vue'
export default {
  components: { 
      editProfile 
      },

}
</script>

<style>

</style>
