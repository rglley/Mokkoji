<template>
  <!-- TODO : 프로필 사진 업로드, 계좌번호 입력란, 이메일 주소 입력란 구현 haeyo-->
  <div class="mx-auto space-y-6">
    <h1 class="text-3xl ml-8 text-center">회원가입 추가정보 입력페이지</h1>
    <div class="flex flex-row gap-10 h-max">
      <div class="flex-auto w-max m-5">
        <label>
          <img id="image-profile" src="@/assets/profile_icon.jpg" />
        </label>
        <input
          class="h-10 w-60 rounded-md border-2 border-slate-200 bg-background px-1 py-2 text-sm file:border-0 file:bg-transparent file:text-sm ml-auto mr-auto"
          id="profile-picture"
          type="file"
          @change="getFileName($event.target.files)"
        />
      </div>
      <div class="flex-auto">
        <div class="p-2">
          <label class="text-sm font-light"> 계좌 </label>

          <div class="flex flex-row items-baseline">
            <select v-model="bank" id="input" aria-placeholder="은행명" required>
              <option v-for="bank in banks" :key="bank" :value="bank">
                {{ bank }}
              </option>
            </select>
            <input id="input" class="w-64" placeholder="계좌번호를 입력하세요" v-model="accountNumber" />
          </div>
        </div>
        <div class="p-2">
          <label class="text-sm font-light mr-5">Email</label
          ><input
            id="input"
            class="w-72"
            placeholder="email@example.com"
            required=""
            type="email"
            v-model="email"
          />
        </div>
        <button
          class="my-6 items-center whitespace-nowrap rounded-md text-sm font-medium bg-primary hover:bg-primary/90 h-10 w-full"
          type="submit"
        >
          회원가입
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

let fileName = ref('')

let getFileName = async (files) => {
  fileName.value = files[0].name
  await base64(files[0])
}

let base64 = (file) => {
  return new Promise((resolve) => {
    let reader = new FileReader()
    reader.onload = (e) => {
      resolve(e.target.result)
      let previewImage = document.getElementById('image-profile')
      previewImage.src = e.target.result
    }
    reader.readAsDataURL(file)
  })
}

let banks = ['KB', '농협', '기업', '카카오뱅크']
let bank = ref('')
let accountNumber = ref('')
// TODO : 정규표현식으로 계좌번호, 이메일 Validation
// 필요여부 불투명

let email = ref('')
</script>

<style scoped>
</style>