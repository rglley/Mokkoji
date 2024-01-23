<template>
  <h1 id="title-sub-bold">모꼬지 시작하기</h1>
  <div v-if="loggedin">
    <p>이름 : {{user.name}}</p>
    <p>{{user.email}}</p>
    <button @click="logout">로그아웃</button>
  </div>
  <div v-else>
    <GoogleLogin :callback="callback"></GoogleLogin>

  </div>
  <h1>NAVER LOGIN</h1>
  <div class="button-container"></div>
</template>
  
<script setup>
import { ref } from 'vue';
import { decodeCredential, googleLogout } from 'vue3-google-login'

let loggedin = ref(false) // 임시 로그인 설정 (실제로는 store 관리)
let user = ref(null)

let logout = () => {
  googleLogout();
  loggedin.value = false;
}

let callback = (response) => {
  console.log(response)
  user.value = decodeCredential(response.credential)
  console.log(user)
  loggedin.value = true
}
</script>
  
<style scoped>
</style>  