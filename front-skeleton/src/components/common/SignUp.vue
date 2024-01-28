<template>
  <div id="main-gradient2" class="py-40">
    <div class="mx-auto w-1/3 bg-white rounded-lg shadow-md p-20">
      <div class="space-y-2 text-center">
        <h1 class="text-3xl font-bold">모꼬지</h1>
        <p class="text-slate-400">회원가입 하세용</p>
      </div>
      <div class="space-y-4">
        <div class="space-y-2">
          <label class="text-sm font-medium text-slate-500" for="email"
            ><span class="text-red-500">*</span> Email
          </label>
          <input
            class="flex h-10 w-full bg-background px-3 py-2 text-sm border-2 border-gray-300 rounded-md"
            placeholder="m@example.com"
            v-model="email"
            required="true"
          />
          <p v-show="!validEmail">올바른 이메일을 입력해 주세요</p>
        </div>
        <div class="space-y-2">
          <label class="text-sm font-medium text-slate-500" for="password">
            <span class="text-red-500">*</span>비밀번호
          </label>
          <input
            class="flex h-10 w-full bg-background px-3 py-2 text-sm border-2 border-gray-300 rounded-md"
            id="password"
            type="password"
            v-model="pwInput"
          />
        </div>
        <div class="space-y-2">
          <label class="text-sm font-medium text-slate-500" for="confirm-password"
            ><span class="text-red-500">*</span> 비밀번호 확인
          </label>
          <input
            class="flex h-10 w-full bg-background px-3 py-2 text-sm border-2 border-gray-300 rounded-md"
            id="confirm-password"
            type="password"
            v-model="pwConfirm"
          />
          <p class="text-red-500 text-xs"></p>
        </div>

        <div class="flex items-center space-x-4">
          <div class="flex-auto w-max m-5">
            <label>
              <img id="image-profile" src="@/assets/profile_icon.jpg" />
            </label>
            <input
              class="mx-auto h-10 w-full rounded-md border-2 border-slate-200 bg-background px-1 py-2 text-sm file:border-0 file:bg-transparent file:text-sm"
              id="profile-picture"
              type="file"
              @change="getFileName($event.target.files)"
            />
          </div>
        </div>
        <div >
            <div class="p-2">
              <label class="text-sm font-light"> 계좌번호 </label>
    
              <div class="flex flex-row items-baseline">
                <select v-model="bank" id="input" aria-placeholder="은행명" required>
                  <option v-for="bank in banks" :key="bank" :value="bank">
                    {{ bank }}
                  </option>
                </select>
                <input id="input" class="w-64" placeholder="계좌번호를 입력하세요" v-model="accountNumber" />
              </div>
            </div>
          </div>
      </div>
      <button class="float-right" @click="submit">회원가입</button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const email = ref("");
const validEmail = ref(true);

const pwInput = ref('');
const pwConfirm = ref('');
const msg = ref([]);

const validateEmail = (value) => {
  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)) {
    msg.value['email'] = '';
  } else {
    msg.value['email'] = 'Invalid Email Address';
  }
};

const validatePassword = (value) => {
  let difference = 8 - value.length;
  if (value.length < 8) {
    msg.value['password'] = 'Must be 8 characters! ' + difference + ' characters left';
  } else {
    msg.value['password'] = '';
  }
};

watch(email, (value) => {
  email.value = value;
  validateEmail(value);
});

watch(pwInput, (value) => {
  pwInput.value = value;
  validatePassword(value);
});

let fileName = ref("");

let getFileName = async (files) => {
  fileName.value = files[0].name;
  await base64(files[0]);
};

let base64 = (file) => {
  return new Promise((resolve) => {
    let reader = new FileReader();
    reader.onload = (e) => {
      resolve(e.target.result);
      let previewImage = document.getElementById("image-profile");
      previewImage.src = e.target.result;
    };
    reader.readAsDataURL(file);
  });
};

let banks = ['KB', '농협', '기업', '카카오뱅크']
let bank = ref('')
let accountNumber = ref('')
</script>

<style scoped></style>
