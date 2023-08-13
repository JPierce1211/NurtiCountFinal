<template>
  <div id="login">
    <form @submit.prevent="login">
      <h1 class="login-title">Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="login-form">
  <div class="login-labels">
    <label for="username">Username</label>
    <br>
    <label for="password">Password</label>
  </div>
  <div class="login-input">
    <input type="text" id="username" v-model="user.username" required autofocus />
    <br>
    <input type="password" id="password" v-model="user.password" required />
    <br>
  </div>
  <div class="submit-button-container">
    <button type="submit" class="login-button">Sign in</button>
  </div>
</div>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import ProfileService from "../services/ProfileService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status === 200) 
          { 
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$store.commit("SET_USER_ID", response.data.user.id); 

            ProfileService.getProfileInfo(response.data.user.id)
              .then(profileResponse => 
              { 
                if (profileResponse.status === 200) 
                { 
                  if (profileResponse.data.profileId === null || profileResponse.data.profileId === undefined) 
                  { 

                    this.$router.push("/createProfile/");
                  } 
                  else 
                  {
                    this.$store.commit("SET_PROFILE_ID", profileResponse.data.profileId); 
                    this.$router.push("/");
                  }
                }
              }) 
              .catch(profileError => 
              { 
                console.error("Profile info request error:", profileError);
              });
          }
        }) 
        .catch(error => 
        { 
          const response = error.response;

          if (response.status === 401) 
          { 
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>



<style scoped>

  @import "../style/feel.css";

  .form-input-group 
    {
      margin-bottom: 1rem;
    }
  label 
    {
      margin-right: 0.5rem;
    }
</style>