import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index.js'
import editProfile from '../views/EditProfile.vue'
import createProfile from '../views/CreateProfile.vue'
import Profile from '../views/Profile.vue'
import meal from '../views/Meal.vue'
import FoodDetails from '../components/FoodDetails.vue'
//import showFood from '../components/Meals.vue'
import progress from '../views/Progress.vue'
import MealsDetails from '../components/MealsDetails.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/meals/:id",
      name: "meals",
      component: meal,

    },
    {
      path: "/profile/",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/createProfile/",
      name: "createProfile",
      component: createProfile,
    },
    {
      path: "/profile/:id/edit",
      name: "editProfile",
      component: editProfile,
    },
    {
      path: "/myMeal",
      name:"showMeals",
      component: meal,
    },
    {
      path:"/myMeal/food-details",
      name:"foodDetails",
      component: FoodDetails
    },
    {
      path: "/progress",
      name: "progress",
      component: progress,
    },
    {
      path: "/myMeal/meal-detail",
      name:"mealDetails",
      component:MealsDetails
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
