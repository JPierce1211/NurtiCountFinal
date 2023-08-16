<template>
  <div class="profile-page">
    <DBProfile />

    <div class="profile-info">
      <br />
      <h4>{{ $store.state.profile.displayName }}'s Height:</h4>
      <p>
        {{ $store.state.profile.height }} Inches
      </p>
      <br />
      <h4>{{ $store.state.profile.displayName }}'s Weight:</h4>
      <p>
        {{ $store.state.profile.currentWeight }}
      </p>
      <br />
      <h4>{{ $store.state.profile.displayName }}'s Goal Weight:</h4>
      <p>
        {{ $store.state.profile.goals }}
      </p>
      <br />
      <h4>{{ $store.state.profile.displayName }}'s Birthday:</h4>
      <p>
        {{ formatDate(this.$store.state.profile.birthday) }}
      </p>
      <br />
      <br />
    </div>

    <router-link class="nav-item" v-bind:to="{ name: 'createProfile' }">
      Edit Profile
    </router-link>
  </div>
</template>

<script>
import DBProfile from "./DBProfile.vue";

export default 
{
  components: 
  {
    DBProfile,
  },
      /// Might need to delete method below

  methods: 
    {
      formatDate(dateString) 
        {
          const options = { year: 'numeric', month: 'long', day: 'numeric', timeZone: 'UTC' };
          return new Date(dateString).toLocaleDateString(undefined, options);
        }
    }


};
</script>



<style scoped>
.profile-page {
  display: flex;
  flex-direction: column;
  justify-items: center;
  justify-content: space-around;
  padding: 50px;
}

div.profile-info > h4 {
  font-size: 20pt;
}

div.profile-info > p {
  font-size: 16pt;
  font-style: italic;
}

.profile-info {
  text-align: center;
}
</style>