<template>
  <div>
    <h4>Star Rating:</h4>
    <div class="star-rating">
      <img
        v-for="star in stars"
        :key="star"
        :src="starImagePath"
        alt="Star"
        class="star"
      />
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    stars() {
      const starCount = this.$store.state.starRating;
      console.log("star Rating is " + starCount);
      if (starCount <= 5) {
        return Array(starCount).fill(null);
      } else {
        return [null];
      }
    },
    starImagePath() {
      return '@/imgs/pngs/star.jpg';
    },
  },
};
</script>

<style scoped>
.star-rating {
  display: inline-flex;
  align-items: center;
}

.star {
  width: 20px; /* Adjust the width as needed */
  height: 20px; /* Adjust the height as needed */
}
</style>
