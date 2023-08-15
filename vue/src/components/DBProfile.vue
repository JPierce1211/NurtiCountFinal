<template>
  <div class="profile-container">

    <h3 class="center-text">{{ displayName }}</h3>
    <br/>
    <div class="db-profile-pic">
      <img :src="proPicURL" alt="Profile Image" class="profile-pic" />
    </div>
    <div class="db-profile-stars">
      <profileStars />
    </div>
  </div>
</template>

<script>
import ProfileService from '../services/ProfileService.js';
import profileStars from './ProfileStars.vue';

export default 
{
    components: 
    {
        profileStars,
    },

    data() 
    {
        return {
        displayName: '',
        picId: null,
        picFileName: '',
        proPicURL: '',
        };
    },

    async mounted() 
    {
        await this.getProfileData();
        await this.getProfilePicUrl(this.picId);
        //await alert(this.proPicURL + ' is the URL for your profile picture');
    },

    methods: 
    {

        async getProfileData() 
        {
            try 
                {
                    const response = await ProfileService.getProfileInfo(this.$store.state.userId);


                    this.displayName = response.data.displayName;
                    this.picId = response.data.profilePicId;
                    this.picFileName = ProfileService.getPicUrl(this.picId);

                    return response;

                } 
            catch (error) 
                {
                    console.error('Error fetching profile data:', error);
                }
        },

        async getProfilePicUrl(picId)
        {
            if (picId > 0 || picId < 10)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
            return this.proPicURL;
        },
    },
};
</script>

<style scoped>

.profile-container
{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    justify-items: center;
    align-items: center;    
}

div.profile-container > h3 
{
    font-size: 24pt;
}

.profile-pic
{
    width: 80px;
}

</style>