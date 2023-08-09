<template>
    <div>
        <h1>Edit Profile</h1>
    <form @submit.prevent="submitForm">
     <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>   
         <div class="form">
            <label for="display-name"> Display Name:</label>
            <input type="text" id="display_name" v-model="displayName" />
            <div>
                <label for="birthdate">Birthdate:</label>
                <input type="date" id="birthdate" v-model="birthdate" />
            </div>
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
      </div>
      <div>
        <label for="profile_pic_id">Profile Picture:</label>
        <label>
        <input type="radio" id="profilePics" name="profilePic" v-model="profilePicId" value="01" />
        <img :src="imgPP01" alt="Man">
      </label>
      <label>
        <input type="radio" id="profilePics" name="profilePic" v-model="profilePicId" value="02" />
        <img :src="imgPP02" alt="Flower">
      </label>
      <label>
        <input type="radio" id="profilePics" name="profilePic" v-model="profilePicId" value="03" />
        <img :src="imgPP03" alt="Runners">
      </label>
      <label>
        <input type="radio" id="profilePics" name="profilePic" v-model="profilePicId" value="04" />
        <img :src="imgPP04" alt="Music Note">
      </label>
      <label>
        <input type="radio" id="profilePics" name="profilePic" v-model="profilePicId" value="05" />
        <img :src="imgPP05" alt="The Healthiest of Meals">
      </label>


      </div>
      <div>
        <label for="starting_weight">Starting Weight:</label>
        <input type="number" id="starting_weight" v-model="startingWeight" />
      </div>
      <button class="button-submit" type="submit">Submit</button>
      <button class="button-cancel" v-on:click="cancelForm" type="button">Cancel</button>
    </form>
  </div>
</template>

<script>
import profileService from '../services/ProfileService';
export default {
    props:{
        // profileId
        // To add to Profile.vue v-bind:userID="parseInt($route.params.userId)"
        userId: {
            type: Number,
            default: 0
        }
    },
    data(){
        return{
             profile: { 
                // userId: '', 
                height: '',
                gender: '',
                displayName: '',
                birthdate: '',
                profilePicId: '',
               // startingWeight: ''
            },
            errorMsg:""
        };
    },
    methods: {
        submitForm(){
          let newForm = {  
            //  might have to change the id to userid  
          profileId: Number(this.$route.params.profileId), 
          height: this.profile.height,
          gender: this.profile.gender,
          display_name: this.profile.displayName,
          birthdate: this.profile.birthdate,
          profile_pic_id: this.profile.profilePicId,
          starting_weight: this.profile.startingWeight
          };

          newForm = this.profileId;
          newForm.height = this.profile.height;
          newForm.display_name = this.profile.displayName;
          newForm.birthdate = this.profile.birthdate;
          newForm.profile_pic_id = this.profile.profilePicId;
         // newForm.starting_weight = this.profile.starting_weight;
          // thinking starting weight is going to have to change to current weight. How to grab that info
          profileService
          .updateCard(newForm)
          .then(response => {
              if(response.status === 200){
                  this.$router.push(`/profile/${newForm.profileId}`);
            }
            })
          .catch(error => {
              this.handleErrorResponse(error,"adding");
            });
        }
    },
    cancelForm(){
        this.$router.push(`/profile/${this.$route.params.profileId}`);
    }
    
}
</script>

<style>

</style>