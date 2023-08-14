<template>
  <div>
    <h3>{{ displayName }}</h3>
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
        await alert(this.proPicURL + ' is the URL for your profile picture');
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

                     // I dont know why this keeps returning an object when postman returns a string from the same endpoint

                    //this.proPicURL = require(`@/imgs/pngs/` + this.picFileName);

                } 
            catch (error) 
                {
                    console.error('Error fetching profile data:', error);
                }
        },

        async getProfilePicUrl(picId)
        {
            if (picId === 1)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
            if (picId === 2)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
            if (picId === 3)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
            if (picId === 4)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
            if (picId === 5)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
            if (picId === 6)
                {
                    this.proPicURL = require(`@/imgs/pngs/0` + picId + `.png`);
                }
        },
    },
};
</script>

<style scoped>
.profile-pic
{
    width: 80px;
}
</style>