<template>
  <div>
    <form @submit.prevent="submitForm">
         <div>
              <label for="height">Height:</label>
              <select id="height" v-model="height">
                <option value="53">4' 5"</option>
                <option value="54">4' 6"</option>
                <option value="55">4' 7"</option>
                <option value="56">4' 8"</option>
                <option value="57">4' 9"</option>
                <option value="58">4' 10"</option>
                <option value="59">4' 11"</option>
                <option value="60">5' 0"</option>
                <option value="61">5' 1"</option>
                <option value="62">5' 2"</option>
                <option value="63">5' 3"</option>
                <option value="64">5' 4"</option>
                <option value="65">5' 5"</option>
                <option value="66">5' 6"</option>
                <option value="67">5' 7"</option>
                <option value="68">5' 8"</option>
                <option value="69">5' 9"</option>
                <option value="70">5' 10"</option>
                <option value="71">5' 11"</option>
                <option value="72">6' 0"</option>
                <option value="73">6' 1"</option>
                <option value="74">6' 2"</option>
                <option value="75">6' 3"</option>
                <option value="76">6' 4"</option>
                <option value="77">6' 5"</option>
                <option value="78">6' 6"</option>
                <option value="79">6' 7"</option>
                <option value="80">6' 8"</option>
                <option value="81">6' 9"</option>
                <option value="82">6' 10"</option>
                <option value="83">6' 11"</option>
                <option value="84">7' 0"</option>
                <option value="85">7' 1"</option>
                <option value="86">7' 2"</option>
                <option value="87">7' 3"</option>
                <option value="88">7' 4"</option>
              </select>
      </div>
      <div>
        <label for="display_name">Display Name:</label>
        <input type="text" id="display_name" v-model="displayName" />
      </div>
      <div>
        <label for="birthdate">Birthdate:</label>
        <input type="date" id="birthdate" v-model="birthdate" />
      </div>
      <div>
        <label for="profile_pic_id">Profile Picture ID:</label>
        <input type="dropbox" id="profile_pic_id" v-model="profilePicId" />
      </div>
      <div>
        <label for="starting_weight">Starting Weight:</label>
        <input type="number" id="starting_weight" v-model="startingWeight" />
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>
<script>
import profileService from "../services/ProfileService";

export default 
{
  data() 
  {
    return {
      profile: 
      {  
        height: '',
        displayName: '',
        birthdate: '',
        profilePicId: '',
        startingWeight: 0
      }
    }; 
 },
 
    methods: 
    {
        submitForm() 
        {
        const formData = 
        {
                userId: Number(this.$route.params.userId),
               // profileId: Number(this.$route.params.profileId),
                height: this.height,
                display_name: this.displayName,
                birthdate: this.birthdate,
                profile_pic_id: this.profilePicId,
                starting_weight: this.startingWeight
        };
        //CardForm kanabn 
        if(this.userId === 0){
            profileService
                .addProfile(formData)
                .then(response => {
                    if (response.status === 201){
                        this.$router.push(`/profile/${formData.userId}`);
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error, "updating");
                });
            } 
        },   
    },
}
</script>

<style>

</style>