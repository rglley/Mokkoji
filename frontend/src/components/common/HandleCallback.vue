<template>
  <div class="flex space-x-2 justify-center items-center h-screen" id="main-gradient">
    <div class="flex rounded-full bg-white w-[50vh] h-[50vh] items-center justify-center">
      <span class="sr-only">Loading...</span>
      <div class="h-8 w-8 bg-black rounded-full animate-bounce [animation-delay:-0.3s]"></div>
      <div class="h-8 w-8 bg-black rounded-full animate-bounce [animation-delay:-0.15s]"></div>
      <div class="h-8 w-8 bg-black rounded-full animate-bounce"></div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { toast } from 'vue3-toastify'
import axios from 'axios'
import tokenService from '@/services/token.service'

const router = useRouter()
const route = useRoute()
const store = useUserStore()
const naverquerycode = ref('')

onBeforeMount(() => {
  naverquerycode.value = route.query.code
})

onMounted(() => {
  axios({
    method: 'GET',
    // url: "localhost:8080/api/vping"
    url:
      import.meta.env.VITE_API_URL +
      import.meta.env.VITE_SERVER +
      '/oauth2/naver/' +
      naverquerycode.value
  })
    .then((res) => {
      const token = res.headers['authorization']
      tokenService.setLocalAccessToken(token)
      tokenService.setUser(res.data)
      store.name = res.data.name
      store.email = res.data.email
      store.image = res.data.image
      if (res.data.first == true) {
        router.push('/signup')
      } else {
        store.isLogin = true
        const refreshToken = res.headers['authorization-refresh']
        tokenService.setLocalRefreshToken(refreshToken)
        this.$nextTick(() => {
          router.push('/')
        })
      }
    })
    .catch((err) => {
      toast(err.message)
    })
})
</script>

<style></style>
