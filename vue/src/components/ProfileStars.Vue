<template>
  <div>
    <div v-for="(badge, index) in badges" :key="index" class="star-badge">
      {{ badge }}
    </div>
  </div>
</template>

<script>
import mealService from "../services/MealService.js";

export default {
  data() {
    return {
      badges: [],
      consecutiveMeals: 0,
    };
  },
  mounted() {
    this.loadMeals();
  },
  methods: {
    async loadMeals() {
      try {
        const meals = await mealService.getMeals();
        this.calculateConsecutiveMeals(meals);
        this.updateBadges();
      } catch (error) {
        console.error("Error loading meals", error);
      }
    },
    calculateConsecutiveMeals(meals) {
      this.consecutiveMeals = 0;
      let prevLogDay = null;

      for (const meal of meals) {
        const logDay = meal.logDay;
        if (prevLogDay) {
          const daysDifference = this.calculateDaysDifference(logDay, prevLogDay);
          if (daysDifference <= 1) {
            this.consecutiveMeals++;
          } else {
            this.consecutiveMeals = 0;
          }
        } else {
          this.consecutiveMeals = 0;
        }
        prevLogDay = logDay;
      }
    },
    calculateDaysDifference(date1, date2) {
      const oneDay = 24 * 60 * 60 * 1000; 
      const firstDate = new Date(date1);
      const secondDate = new Date(date2);
      const diffDays = Math.round(Math.abs((firstDate - secondDate) / oneDay));
      return diffDays;
    },
    updateBadges() {
      this.badges = [];
      if (this.consecutiveMeals > 2) {
        if (this.consecutiveMeals <= 9) {
          this.badges.push(`${this.consecutiveMeals} 🌟`);
        } else {
          this.badges.push(`${this.consecutiveMeals}+ 🌟`);
        }
      }
    },
  },
};
</script>

<style>
.star-badge {
  font-size: 24px;
  margin-right: 5px;
}
</style> 