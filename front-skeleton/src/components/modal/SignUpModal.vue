<template>
  <!-- TODO : 프로필 사진 업로드, 계좌번호 입력란, 이메일 주소 입력란 구현 haeyo-->
  <div class="mx-auto space-y-6">
    <h1 class="text-3xl ml-8 text-center">회원가입 추가정보 입력페이지</h1>
    <div class="flex flex-row gap-10 h-max">
      <div class="flex-auto w-max m-5">
        <label>
          <img
            id="preview"
            src="@/assets/profile_icon.jpg"
            class="overflow-hidden rounded-full border-2 border-white dark:border-gray w-48 ml-auto mr-auto"
          />
        </label>
        <input
          class="h-10 w-56 rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium ml-auto mr-auto"
          id="profile-picture"
          type="file"
          @change="getFileName($event.target.files)"
        />
      </div>
      <div class="flex-auto">
        <div class="p-2">
          <label class="text-sm font-light"> 계좌번호 </label>

          <div class="flex flex-row items-baseline">
            <select
              v-model="bank"
              class="flex rounded-md h-10 border border-input text-sm text-gray-500 focus:outline-none focus:border-gray-200 peer"
              aria-placeholder="은행명"
              required
            >
              <option v-for="bank in banks" :key="bank" :value="bank">
                {{ bank }}
              </option>
            </select>
            <input
              class="flex h-10 w-60 rounded-md border border-input bg-background px-3 py-2 text-sm placeholder:text-muted-foreground"
              id="account-number"
              placeholder="계좌번호를 입력하세요"
              v-model="accountNumber"
            />
            
          </div>
        </div>
        <div class="p-2">
          <label class="text-sm font-light">Email</label
          ><input
            class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
            id="email"
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
      let previewImage = document.getElementById('preview')
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