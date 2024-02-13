<template>
  <main>
    <TheHeader v-if="!isMeeting" :key="reload" />
    <RouterView
      @load-home="leaveMeeting"
      @create-meeting="createMeeting"
      @leave-meeting="leaveMeeting"
      @waiting-room="createMeeting"
    />
    <TheFooter v-if="!isMeeting" />
  </main>
</template>

<script setup>
import { ref, watchEffect, onBeforeMount } from 'vue'
import TheHeader from './components/common/TheHeader.vue'
import TheFooter from './components/common/TheFooter.vue'
import { RouterView } from 'vue-router'

const isMeeting = ref(false)

const createMeeting = () => {
  isMeeting.value = true
}

const leaveMeeting = () => {
  isMeeting.value = false
}

const reload = ref(0)

const reloadMethod = () => {
  reload.value++
}

watchEffect(
  ($cookies.get('user'),
  async () => {
    reloadMethod
  })
)

onBeforeMount(() => {
  $cookies.keys().forEach((cookie) => $cookies.remove(cookie));
})
</script>

<style scoped></style>
