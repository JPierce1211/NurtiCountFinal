<template>
  <div>
    <br>
    <br>
    <h1 class="center-text">Create a Profile</h1>
    <br>
    <hr style="width: 70%;"> <!-- Replace this with image for hr -->
    <br>


    <form @submit.prevent="submitForm">


      <div class="profile-pic-choice" id="PP-choice">
        <label for="profile_pic_id" class="PP-label">Choose Your Profile Avatar</label>
          <br>
          <div class="PP-pics">
            <label>
              <input type="radio" id="profilePics" v-model="profile.profilePicId" value="01" />
              <img :src="imgPP01" alt="Man" class="profilePicSelector">
            </label>
            <label>
              <input type="radio" id="profilePics" v-model="profile.profilePicId" value="02" />
              <img :src="imgPP02" alt="Flower" class="profilePicSelector">
            </label>
            <label>
              <input type="radio" id="profilePics" v-model="profile.profilePicId" value="03" />
              <img :src="imgPP03" alt="Runners" class="profilePicSelector">
            </label>
            <label>
              <input type="radio" id="profilePics" v-model="profile.profilePicId" value="04" />
              <img :src="imgPP04" alt="Music Note" class="profilePicSelector">
            </label>
            <label>
              <input type="radio" id="profilePics" v-model="profile.profilePicId" value="05" />
              <img :src="imgPP05" alt="The Healthiest of Meals" class="profilePicSelector">
            </label>
          </div>
      </div>
      <br>
      <hr style="width: 70%">
      <br>
      <p class="instruction-label"> Please input more information for your Profile </p>

        <br>
           <div class="height-choice">
              <label for="height" id="height-label">Height&nbsp;&nbsp;&nbsp;</label>
              <select id="height" v-model="profile.height" class="text-box" value="Choose One">
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
        <label for="displayName" id="display-name-label">Display Name&nbsp;&nbsp;&nbsp;</label>
        <input type="text" id="displayName" v-model="profile.displayName" class="text-box"/>
      </div>
      
      <div class="birth-date-input">
        <label for="birthDay" id="birthday-label"> When is your birthday?&nbsp;&nbsp;&nbsp;</label>
        <input type="date" id="birthDay" class="text-box" v-model="profile.birthDay" placeholder="Tell Us Your Birthdate">       
      </div> 


      <div class="current-weight">
        <label for="currentWeight" id="weight-label">Starting Weight&nbsp;&nbsp;&nbsp;</label>
        <input type="number" id="currentWeight" v-model="profile.currentWeight" class="text-box" />
      </div>

      <div class="goal-weight">
        <label for="goals" id="goal-label">Desired Weight&nbsp;&nbsp;&nbsp;</label>
        <input type="number" id="goals" v-model="profile.goals" class="text-box" />
      </div>
      <div id="submit-button">
        <button class="login-button" type="submit">Submit</button>
      </div>
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
        displayName: '',
        birthDay: '',
        profilePicId: '',
        currentWeight: '',
        goals: ''
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

    formatDateForSQL(date) 
      {
        const d = new Date(date);
        const year = d.getFullYear();
        const month = String(d.getMonth() + 1).padStart(2, '0');
        const day = String(d.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
      },

    submitForm() 
      {        
        const fullBirthday = this.formatDateForSQL(this.profile.birthDay);

        const formData = 
          {
            userId: this.$store.state.profile.userId, 
            height: this.profile.height,
            displayName: this.profile.displayName,
            birthday: fullBirthday,
            profilePicId: this.profile.profilePicId,
            currentWeight: this.profile.currentWeight,
            goals: this.profile.goals
          };

          console.log(fullBirthday);

        alert(fullBirthday);

        
        ProfileService
          .createProfile(formData)
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
      if (error.response) 
      {
        this.errorMsg =
          "Error " + verb + " card. Response received was '" +
          error.response.statusText +
          "'.";
      } 
      else if (error.request) 
      {
        this.errorMsg =
          "Error " + verb + " card. Server could not be reached.";
      } 
      else {
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

.height-choice, .display-name-choice, .birth-date-input, .current-weight, .goal-weight, #submit-button
{
  display:grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "label input";
  justify-items: stretch;
  align-items: center;
}

#height-label, #display-name-label, #birthday-label, #weight-label, #goal-label
{
  grid-area: label;
  text-align: end;
}

#height, #displayName, #birthDay, #currentWeight, #goals, .login-button
{
  grid-area: input;
  justify-items: flex-start;
}

.profile-pic-choice
{
  display: grid;
  grid-template-rows: 20px 1fr;
  grid-template-areas: "label" "choice";
  align-items: center;
  justify-items: center;
}

.PP-label
{
  grid-area: label;
  font-size: 20px;
}

.instruction-label
{
  font-size: 20px;
  text-align: center;
}
.PP-pics
{
  grid-area: choice
}
</style>