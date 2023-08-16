<template>
  <div class="profile-container">
    <h3 class="center-text">{{ $store.state.profile.displayName }}</h3>

    <br />
    <div class="db-profile-pic">
      <img :src="proPicURL" alt="Profile Image" class="profile-pic" />
    </div>
    <div class="db-profile-stars">
      <profileStars />
    </div>
  </div>
</template>

<script>
import profileStars from './ProfileStars.vue';


export default 
{
  components:
    {
      profileStars
    },
    data()
    {
        return{
            proPicURL: null,
        };
    },
    methods: 
        {
            logProfileData() 
                {
                console.log(this.$store.state.profile);
                },
            getProfilePicUrl(picId) 
                {
                    if (picId > 0 || picId < 10) 
                        {
                          console.log(this.$store.state.profile.displayName + "'s birthday is " + this.$store.state.profile.birthday);

                          const realPicId = parseInt(picId, 10);

                          this.proPicURL = require(`@/imgs/pngs/0` + realPicId + `.png`);
                        }
                },
        },
            mounted() 
                {
                    this.logProfileData();
                    this.getProfilePicUrl(this.$store.state.profile.profilePicId);
                },
};
</script>

<style scoped>
.profile-container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  justify-items: center;
  align-items: center;
}

div.profile-container > h3 {
  font-size: 36pt;
  word-wrap: break-word;
}

.profile-pic {
  width: 80px;
}
</style>