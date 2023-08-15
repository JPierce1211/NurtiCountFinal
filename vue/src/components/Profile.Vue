<template>
    <div class="profile-page">

        <DBProfile />

        <div class="profile-info">
        <br>    
            <h4>
                {{profile.displayName}}'s Height:
            </h4>
            <p>
                {{profile.height}}
            </p>
        <br>
            <h4>
                {{profile.displayName}}'s Weight:
            </h4>
            <p>
                {{profile.currentWeight}}
            </p>
        <br>
            <h4>
                {{profile.displayName}}'s Goal Weight:
            </h4>
            <p>
                {{profile.goals}}
            </p>            
        <br>
            <h4>
                {{profile.displayName}}'s Birthday:
            </h4>
            <p>
                {{profile.birthDay}}
            </p>
        <br>
        <br>
        </div>

        <router-link class="nav-item" v-bind:to="{ name: 'createProfile' }"> Edit Profile </router-link>

    </div>
</template>

<script>

import DBProfile from './DBProfile.vue';

export default {

    components: 
    {
      DBProfile,

    },

    data()
    {
        return {
            profile: 
            {
                userId: '',
                height: '',
                displayName: '',
                birthDay: '',
                profilePicId: '',
                currentWeight: '',
                goals: '',
            }
        }
    },

    async mounted()
    {
        await this.getProfileInfo();
        await this.getProfilePicUrl(this.picId);
    },

    methods:
    {
        async getProfileInfo()
        {
            this.profile = await DBProfile.getProfileData(this.$store.state.userId);
        }
    }

}

</script>



<style scoped>

.profile-page
{
    display: flex;
    flex-direction: column;
    justify-items: center;
    justify-content: space-around;
    padding: 50px;
}

div.profile-info > h4
{
    font-size: 16pt;
}

div.profile-info > p
{
    font-size: 12pt;
}

.profile-info
{
    text-align: center;
}

</style>