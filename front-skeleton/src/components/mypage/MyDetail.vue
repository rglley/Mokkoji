<template>
  <div class="w-full h-screen bg-gray-100 px-10 pt-10">
    <div class="relative mb-32 max-w-sm mx-auto mt-24">
        <div id="card-div">
        <div class="absolute -mt-20 w-full flex justify-center">
          <div class="h-32 w-32">
            <img
              src="@/assets/dummy_profile.jpg"
              class="overflow-hidden rounded-full border-4 border-white dark:border-gray"
            />
          </div>
          <router-link to="/mypage/account" id="button-submit">계좌 정보 수정</router-link>

        </div>
       
        
      </div>
    </div>
  </div>
  <!-- <input type="file" @change="handleFileUpload"> -->

</template>
  
<script setup>
// axios로 user의 profile pic을 가져올 예정
// import {axios} from 'axios';




</script>
  
<style scoped>
</style>
  