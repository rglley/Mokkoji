<template>
  <div class="w-full h-screen bg-gray-100 px-10 pt-10">
    <div class="relative mb-32 max-w-sm mx-auto mt-24">
      <div id="card-div">
        <div class="relative -mt-20 w-full justify-center">
          <div class="h-32 w-32">
            <img
              id="preview"
              src="@/assets/profile_icon.jpg"
              class="overflow-hidden rounded-full border-2 border-white dark:border-gray w-48 ml-28"
            />
          </div>
          <label>프로필 사진 선택 </label>
          <p>qwe</p>
          <input
            class="my-10 h-10 w-56 rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium"
            id="profile-picture"
            type="file"
            @change="getFileName($event.target.files)"
          />
        </div>
        <div class="flex flex-col">
          <button id="button-submit" class="w-48">
            <router-link to="/mypage/account">계좌 정보 수정</router-link>
          </button>
          <button class="w-20 bg-gray text-red-400 mt-10 mr-auto" @click="store.withdraw">회원 탈퇴</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import { ref } from 'vue'
import { useUserStore } from '../../stores/user'

const store = useUserStore();

const fileName = ref('')

const getFileName = async (files) => {
  fileName.value = files[0].name
  await base64(files[0])
}

const base64 = (file) => {
  return new Promise((resolve) => {
    const reader = new FileReader()
    reader.onload = (e) => {
      resolve(e.target.result)
      const previewImage = document.getElementById('preview')
      previewImage.src = e.target.result
    }
    reader.readAsDataURL(file)
  })
}
</script>
  
<style scoped>
</style>
  