<template>
    <div>
        <h3>{{ this.$tore.state.profile.displayName }}</h3>
        <div class="db-profile-pic">
            <img :src="proPicURL" alt="Profile Image" />
        </div>
        <div class="db-profile-stars">
            
        </div>
    </div>
</template>

<script>
//import axios from 'axios';
import ProfileService from '../services/ProfileService.js'

export default {
    data() {
        return {
            displayName: '', // To store the profile name
            proPicURL: '/path/to/default/image.png', // Replace with actual URL
        };
    },
    mounted() {
        this.fetchProfileData();
    },
    methods: {
        fetchProfileData() {
            // const userId = 2; 
            ProfileService
                .getProfileInfo(this.$store.state.profile.userId)
                .then(response => {
                    this.displayName = response.data.displayName; // Assign to displayName
                })
                .catch(error => {
                    console.error('Error fetching profile data:', error);
                });
        },
    },
};
</script>