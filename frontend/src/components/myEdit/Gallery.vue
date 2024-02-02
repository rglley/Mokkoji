<template>
  <div ref="scrollContainer" class="custom-scroll-container">
    <div ref="scrollComponent" class="custom-scroll-content">
      <ul class="photo-grid">
        <li v-for="(photo, index) in photos" :key="index">
          <img :src="`${photo}`" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import getImages from '@/api/get_images'

const photos = ref(getImages(30))
const scrollContainer = ref(null)
const scrollComponent = ref(null)

const loadMorePhotos = () => {
  let newImages = getImages(10)
  photos.value.push(...newImages)
}

const handleScroll = () => {
  let component = scrollComponent.value
  let container = scrollComponent.value
  if (container.scrollTop + container.clientHeight >= component.offsetHeight) {
    loadMorePhotos()
  }
}

onMounted(() => {
  scrollContainer.value.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  scrollContainer.value.removeEventListener('scroll', handleScroll)
})
</script>
<style>
.custom-scroll-container {
  overflow-y: auto;
  height: 600px;
}

.photo-grid {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  grid-template-columns: repeat(5, 1fr); /* 5 columns */
  gap: 16px; /* Adjust the gap between photos as needed */
}

.photo-grid li {
  flex: 0 0 calc(20% - 16px); /* 20% width with a 16px gap */
  margin-bottom: 16px;
}

.photo-grid img {
  width: 100%;
  height: auto;
  display: block;
  border-radius: 8px; /* Add border radius for rounded corners */
}
</style>
