<template>
  <div class="w-80 items-center">
  <div id="title-sub-bold" class="ml-auto mr-auto">모꼬지 시작하기</div>
  <div>
    <img id="login-icon" src="@/assets/login_naver.png" @click="naverLogin"/>
    <img id="login-icon" src="@/assets/login_google.png" @click="googleLogin"/>
  </div>
  </div>
</template>
  
<script setup>
import axios from 'axios';

let naverLogin = () => {
  axios({
    url : '/oauth2/authorization/google',
    method : postMessage,
    data: {
      dummy : 'dummy',
    }
  })
}

let googleLogin = () => {
  axios({
    url : '/oauth2/authorization/google',
    method : postMessage,
    data: {
      dummy : 'dummy',
    }
  })
}

</script>
  
<style scoped>
</style>  