<template>
  <div>
    <br>
    <br>
    <h1 class="center-text">Create a Profile</h1>
    <br>
    <hr style="width: 70%;">
    <br>

    <form @submit.prevent="submitForm">

      <div class="profile-pic-choice" id="PP-choice">
        <label for="profile_pic_id" class="PP-label">Choose Your Profile Avatar</label>
        <br>
        <div class="PP-pics">
          <label v-for="(img, index) in profileImages" :key="index">
            <input type="radio" id="profilePics" v-model="profile.profilePicId" :value="img.id" />
            <img :src="img.src" :alt="img.alt" class="profilePicSelector">
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
          <option v-for="option in heightOptions" :key="option.value" :value="option.value">{{ option.label }}</option>
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

export default {
  data() {
    return {
      profile: {
        userId: this.$store.state.profile.userId,
        height: '',
        displayName: '',
        birthDay: '',
        profilePicId: '',
        currentWeight: '',
        goals: '',
      },
      profileImages: [
        { id: '01', src: require('../imgs/pngs/01.png'), alt: 'Man' },
        { id: '02', src: require('../imgs/pngs/02.png'), alt: 'Flower' },
        { id: '03', src: require('../imgs/pngs/03.png'), alt: 'Runners' },
        { id: '04', src: require('../imgs/pngs/04.png'), alt: 'Music Note' },
        { id: '05', src: require('../imgs/pngs/05.png'), alt: 'The Healthiest of Meals' },
      ],
      heightOptions: [
        { value: '53', label: "4' 5\"" },
        { value: '54', label: "4' 6\"" },
        { value: '55', label: "4' 7\"" },
        { value: '56', label: "4' 8\"" },
        { value: '57', label: "4' 9\"" },
        { value: '58', label: "4' 10\"" },
        { value: '59', label: "4' 11\"" },
        { value: '60', label: "5' 0\"" },
        { value: '61', label: "5' 1\"" },
        { value: '62', label: "5' 2\"" },
        { value: '63', label: "5' 3\"" },
        { value: '64', label: "5' 4\"" },
        { value: '65', label: "5' 5\"" },
        { value: '66', label: "5' 6\"" },
        { value: '67', label: "5' 7\"" },
        { value: '68', label: "5' 8\"" },
        { value: '69', label: "5' 9\"" },
        { value: '70', label: "5' 10\"" },
        { value: '71', label: "5' 11\"" },
        { value: '72', label: "6' 0\"" },
        { value: '73', label: "6' 1\"" },
        { value: '74', label: "6' 2\"" },
        { value: '75', label: "6' 3\"" },
        { value: '76', label: "6' 4\"" },
        { value: '77', label: "6' 5\"" },
        { value: '78', label: "6' 6\"" },
        { value: '79', label: "6' 7\"" },
        { value: '80', label: "6' 8\"" },
        { value: '81', label: "6' 9\"" },
        { value: '82', label: "6' 10\"" },
        { value: '83', label: "6' 11\"" },
        { value: '84', label: "7' 0\"" },
        { value: '85', label: "7' 1\"" },
        { value: '86', label: "7' 2\"" },
        { value: '87', label: "7' 3\"" },
        { value: '88', label: "7' 4\"" },
      ],
    };
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
      

      alert('Profile ID # ' + this.profileId)
      console.log(ProfileService.getProfileInfo(this.$store.state.profile.userId));
      const formData = 
      {
        userId: this.$store.state.profile.userId,
        height: this.profile.height,
        displayName: this.profile.displayName,
        birthday: fullBirthday,
        profilePicId: this.profile.profilePicId,
        currentWeight: this.profile.currentWeight,
        goals: this.profile.goals,
      };


      // if (this.profileId === 0)
      // {
      //   ProfileService.createProfile(formData)
      //       .then(response => 
      //       {
      //         if (response.status === 201) 
      //         {
                  
      //             alert('Profile ID # ' + this.profileId)  // delete this later

      //             this.$router.push(`/`);
      //         }
      //           else
      //         {
      //           alert('Unable to create a new profile'); 
      //         }
      //       })
      //       .catch(error => {
      //         this.handleErrorResponse(error, "creating");
      //   });
      // }
      //   else
      // {
      //   ProfileService.updateProfile(formData.userId, formData)
      //         .then(response => 
      //       {
      //         if (response.status === 200) 
      //         {
                  
      //             alert('Profile ID # ' + this.profileId);  // delete this later

      //             this.$router.push(`/`);
      //         }
      //           else
      //         {
      //           alert('Unable to update profile'); 
      //         }
      //       })


 ////////// Above I attempted to rewrite the code below in a manner consistent with our KanBan lecture excercise material.




      ProfileService.getProfileInfo(this.$store.state.profile.userId)
        .then(response => {
          if (response.data) 
          {
            //alert('response DOT data DOT userid = ' + response.data.userId);
            alert('getProfileInfo() searched and returned an existing profile for this user. Proceeding to update profile')
            this.updateProfile(response.data, formData);
          } 
          else 
          {
            alert('getProfileInfo() searched and returned no existing profile for this user. Proceeding to create profile')
            this.createProfile(formData);
          }
        })
        .catch(error => {
          console.error('Error checking profile existence:', error);
        });
    },

    createProfile(formData) {
      ProfileService.createProfile(formData)
        .then(response => {
          if (response.status === 201) {
            //this.$store.state.user.hasProfile = true;
 
            this.$store.commit("SET_PROFILE_ID", response.data.profileId); 
            this.$router.push(`/`);
            
          }
          else
          {
            alert('Unable to create a new profile'); 
          }
        })
        .catch(error => {
          this.handleErrorResponse(error, "creating");
        });
    },

    updateProfile(existingProfile, formData) {
      ProfileService.updateProfile(existingProfile.userId, formData)
        .then(response => {
          if (response.status === 200) {
            this.$router.push(`/`);
          }
        })
        .catch(error => {
          console.error('Error updating profile:', error);
          alert('Error updating profile: ' + error);
        });
    },

    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " profile. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " profile. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " profile. Request could not be created.";
      }
    },
  },
};

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