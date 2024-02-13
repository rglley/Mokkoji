<template>
  <div class="h-[100vh] w-[100vw]"></div>
  <swiper :pagination="pagination" :modules="modules" class="mySwiper">
    <swiper-slide v-for="(msg, index) in Messages" :key="`${index}`">
      <!-- <div v-for="(msg, index) in Messages" :key="`${index}`"> -->
      <RecollectionList
        v-for="recollection in recollections"
        :key="recollection.eventId"
        :recollection="recollection"
      />
      <!-- </div> -->
    </swiper-slide>
  </swiper>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue'

// Import Swiper styles
import 'swiper/css'

import 'swiper/css/pagination'

// import './style.css'

// import required modules
import { Pagination } from 'swiper/modules'
import RollingPaperItem from '@/components/myresult/RollingPaperItem.vue'
import RecollectionList from '@/components/myevent/RecollectionList.vue'

export default {
  components: {
    Swiper,
    SwiperSlide,
    RollingPaperItem,
    RecollectionList
  },
  setup() {
    return {
      pagination: {
        clickable: true,
        renderBullet: function (index, className) {
          return '<span class="' + className + '">' + (index + 1) + '</span>'
        }
      },
      modules: [Pagination]
    }
  }
}
</script>
