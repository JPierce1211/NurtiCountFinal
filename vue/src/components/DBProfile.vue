<template>
    <div>
        <h3>{{ displayName }}</h3>
        <div class="db-profile-pic">
            <img :src="proPicURL" alt="Profile Image" />
        </div>
        <div class="db-profile-stars">
            <profileStars />
        </div>
    </div>
</template>

<script>
//import axios from 'axios';
import ProfileService from '../services/ProfileService.js'
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
            displayName: '', // To store the profile name
        //    proPicURL: '../imgs/png/' + getProfilePicURL(), 
        };
    },

    

    mounted() 
    {
        this.getProfileData();

        const proPicURL = '../imgs/png/' + this.getProfilePicURL();

        alert('PP_URL = ' + this.getProfilePicURL());
    },
    methods: {
        getProfileData()
        {
            // const userId = 2; 
            ProfileService
                .getProfileInfo(this.$store.state.userId)
                .then(response => {
                    this.displayName = response.data.displayName; // Assign to displayName
                })
                .catch(error => {
                    console.error('Error fetching profile data:', error);
                });
        },
        getProfilePicId(profileInfo) 
        {
            if (profileInfo && typeof profileInfo === "object" && "profilePicId" in profileInfo) 
                {
                    return profileInfo.profilePicId;
                } 
            else 
                {
                    return null; // Return null or handle error condition
                }
        },
        getProfilePicURL()
        {
            const profileInfo = ProfileService.getProfileInfo(this.$store.state.userId); 
                alert('Profile Pic ID from the getprofilepicurl(): ' + profileInfo.profilePicId);
            const profilePicId = this.getProfilePicId(profileInfo.profilePicId);

            return ProfileService.getPicUrl(profilePicId);

        }
    },
};
</script>