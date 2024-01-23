<template>
  <nav class="w-full bg-opacity-40 bg-primary flex p-2 pl-5">
    <a href="/" class="flex items-center rtl:l:space-x-reverse">
      <img src="/src/assets/mokkoji_logo.png" class="h-20" alt="모꼬지 로고" />
      <span id="title-sub-bold">모꼬지</span>
    </a>

    <div class="ml-auto mr-10 self-center">
      <ul class="font-medium flex md:flex-row md:space-x-8 md:mt-0">
        <li>
          <button class="bg-white text-black hover:bg-primary" href="/">홈으로</button>
        </li>
        <li v-if="!isLogin">
          <button class="bg-white text-black hover:bg-primary" @click="toggleModal">로그인</button>
          <ModalView :show-login-modal="showLoginModal" @close-modal="toggleModal">
            <LoginModal />
          </ModalView>

          <button class="bg-white text-black hover:bg-primary" href="/signup">회원가입</button>
        </li>
        <li v-else>
          <button
            class="bg-white text-black hover:bg-primary"
            data-dropdown-toggle="dropdownHover"
            data-dropdown-trigger="hover"
            to="/mypage"
          >
            내 서비스
          </button>
          <div
            id="dropdownHover"
            class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-100"
          >
            <ul aria-labelledby="dropdownHoverButton" class="w-50">
              <li>
                <a href="mypage">마이페이지</a>
              </li>
              <li>
                <a href="#">내 결과물</a>
              </li>
              <li>
                <a @click="logout">로그아웃</a>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </div>

    <ModalView v-if="showLoginModal"> </ModalView>
  </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { initFlowbite } from 'flowbite'

import ModalView from './ModalView.vue'
import LoginModal from './LoginModal.vue'

onMounted(() => {
  initFlowbite()
})

const showLoginModal = ref(false)

const toggleModal = () => {
  showLoginModal.value = !showLoginModal.value
}

let isLogin = ref(true)

let logout = () => {
  //sessionStorage.delete('login-token')
  isLogin.value = false
}
</script>

<style scoped></style>
