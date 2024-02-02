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

const pageSize = 30
const photos = ref([])
const totalPhotos = ref(0)
const scrollContainer = ref(null)
const scrollComponent = ref(null)

const loadPhotos = (count) => {
  const newImages = getImages(count)
  photos.value = newImages
  totalPhotos.value = newImages.length
}

const loadMorePhotos = () => {
  const remainingPhotosCount = totalPhotos.value - photos.value.length
  const newImages = getImages(Math.min(10, remainingPhotosCount))
  photos.value.push(...newImages)
}

const handleScroll = () => {
  const component = scrollComponent.value
  const container = scrollComponent.value
  if (container.scrollTop + container.clientHeight >= component.offsetHeight) {
    loadMorePhotos()
  }
}

onMounted(() => {
  loadPhotos(pageSize)
  scrollContainer.value.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  scrollContainer.value.removeEventListener('scroll', handleScroll)
})

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
  overflow-y: scroll;
  height: 800px;
  scrollbar-width: thin; /* For Firefox */
  scrollbar-color: rgb(255, 255, 255) rgb(190, 212, 244); /* For Firefox */
}

.photo-grid {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 16px;
}

.custom-scroll-container::-webkit-scrollbar {
  width: 8px;
}

.custom-scroll-container::-webkit-scrollbar-thumb {
  background-color: rgb(121, 146, 220);
}

.custom-scroll-container::-webkit-scrollbar-track {
  background-color: rgb(60, 169, 208);
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
