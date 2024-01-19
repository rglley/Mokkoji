<template>
  <router-link to="/">
    <div class="ml-2 h-24">
      <img src="/src/assets/mokkoji_logo.png" class="h-20" />
    </div>
  </router-link>
  <nav>
    <div v-if="isLogin">
      <router-link to="#">결과물 보기</router-link>
      <router-link to="/mypage"> 마이 페이지 </router-link>
      <router-link to="#"> 로그아웃 </router-link>
    </div>
    <div v-else>
      <!--로그인 모달컴포넌트 띄울예정 -->
      <button>로그인</button>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue';
let isLogin = ref(true);

</script>

<style lang="scss" scoped>
</style>