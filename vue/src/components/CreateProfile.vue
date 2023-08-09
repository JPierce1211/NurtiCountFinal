<template>
  <div>
    <h1>Create a Profile</h1>
    <form @submit.prevent="submitForm">
         
           <div class="gender-choice">
              <label for="gender">
                Sex:
              </label>
              <select id="gender" v-model="gender">
                <option value="M"> Male </option>
                <option value="F"> Female </option>
              </select>
           </div>

           <div class="height-choice">
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
      <div class="display-name-choice">
        <label for="display_name">Display Name:</label>
        <input type="text" id="display_name" v-model="displayName" />
      </div>
      <div class="birth-date-choice">
        <label for="birthdate">Birthdate:</label>
        <input type="date" id="birthdate" v-model="birthdate" />
      </div>
      <div class="profile-pic-choice">
        <label for="profile_pic_id">Profile Picture:</label>

        <label>
        <input type="radio" id="profilePics01" v-model="profilePicId" value="01" />
        <img :src="imgPP01" alt="Man" class="profilePicSelector">
      </label>
      <label>
        <input type="radio" id="profilePics02" v-model="profilePicId" value="02" />
        <img :src="imgPP02" alt="Flower" class="profilePicSelector">
      </label>
      <label>
        <input type="radio" id="profilePics03" v-model="profilePicId" value="03" />
        <img :src="imgPP03" alt="Runners" class="profilePicSelector">
      </label>
      <label>
        <input type="radio" id="profilePics04" v-model="profilePicId" value="04" />
        <img :src="imgPP04" alt="Music Note" class="profilePicSelector">
      </label>
      <label>
        <input type="radio" id="profilePics05" v-model="profilePicId" value="05" />
        <img :src="imgPP05" alt="The Healthiest of Meals" class="profilePicSelector">
      </label>


      </div>
      <div class="starter_weight">
        <label for="starting_weight">Starting Weight:</label>
        <input type="number" id="starting_weight" v-model="startingWeight" />
      </div>
      <button class="login-button" type="submit">Submit</button>
    </form>
  </div>
</template>
<script>
import ProfileService from "../services/ProfileService";

export default 
{
  data() 
  {
    return {
      profile: 
      { 
        userId: '', 
        height: '',
        gender: '',
        displayName: '',
        birthdate: '',
        profilePicId: '',
        startingWeight: ''
      }
    }; 
  },

  computed: 
  {
    imgPP01() { 
      return require('../imgs/pngs/01.png')
    },
    imgPP02() { 
      return require('../imgs/pngs/02.png')
    },
    imgPP03() { 
      return require('../imgs/pngs/03.png')
    },
    imgPP04() { 
      return require('../imgs/pngs/04.png')
    },
    imgPP05() { 
      return require('../imgs/pngs/05.png')
    },
  },
 
  methods: 
  {
      submitForm() 
      {
        //let hasProfile = true;
        const formData = 
        {
          userId: this.$store.state.profile.userId, 
          height: this.profile.height,
          gender: this.profile.gender,
          display_name: this.profile.displayName,
          birthdate: this.profile.birthdate,
          profile_pic_id: this.profile.profilePicId,
          starting_weight: this.profile.startingWeight
        };
        ProfileService
          .createProfile(formData.userId, formData)
          .then(response => 
          {
              if (response.status === 201)
              {      
                this.$store.state.user.hasProfile = true; //This could create errors later down the line. 
                this.$router.push(`/`);                     
              }
          })
          .catch(error => 
          {
              this.handleErrorResponse(error, "updating");
          });

 

    }, 
    handleErrorResponse(error, verb) 
    {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " card. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " card. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " card. Request could not be created.";
      }
    }  
  },
}
</script>

<style scoped>
.profilePicSelector
{
  width: 80px;
}
</style>