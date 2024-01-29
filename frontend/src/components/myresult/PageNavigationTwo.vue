<template>
  <swiper :pagination="pagination" :modules="modules" class="mySwiper">
    <swiper-slide></swiper-slide>
    <swiper-slide></swiper-slide><swiper-slide></swiper-slide> <swiper-slide></swiper-slide
    ><swiper-slide></swiper-slide> <swiper-slide></swiper-slide><swiper-slide></swiper-slide>
    <swiper-slide></swiper-slide><swiper-slide></swiper-slide>
  </swiper>
</template>
<script setup>
import { ref } from 'vue'
import { Swiper, SwiperSlide } from 'swiper/vue'
import { Pagination } from 'swiper/modules'
import 'swiper/css'
import 'swiper/css/pagination'
const pagination = ref({
  clickable: true,
  renderBullet: function (index, className) {
    return `<span class="${className}">${index + 1}</span>`
  }
})

const modules = ref([Pagination])
</script>
<style scoped>
#app {
  height: 100%;
}
html,
body {
  position: relative;
  height: 100%;
}

body {
  background: #eee;
  font-family:
    Helvetica Neue,
    Helvetica,
    Arial,
    sans-serif;
  font-size: 14px;
  color: #000;
  margin: 0;
  padding: 0;
}

.swiper {
  width: 100%;
  height: 5%;
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: #fff;

  /* Center slide text vertically */
  display: flex;
  justify-content: center;
  align-items: center;
}

/* .swiper-slide img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
} */

.swiper-pagination-bullet {
  width: 20px;
  height: 20px;
  text-align: center;
  line-height: 20px;
  font-size: 12px;
  color: #000;
  opacity: 1;
  background: rgba(0, 0, 0, 0.2);
}

.swiper-pagination-bullet-active {
  color: #fff;
  background: #007aff;
}
</style>
