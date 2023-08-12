
<template>
<!-- This v-bind makes sure the child is getting and updating information -->
  <edit-profile v-bind:profileID="parseInt($route.params.profileID)" />
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
