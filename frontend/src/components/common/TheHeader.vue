<template>
  <header class="fixed top-0 z-10 w-full h-64">
    <!-- <nav class="w-full bg-opacity-35 p-2 bg-primary flex pl-5"> -->
    <nav class="w-full flex pl-5">
      <a href="/" class="flex items-center rtl:l:space-x-reverse">
        <img
          src="/src/assets/mokkoji_logo.png"
          class="h-28 mx-5 transition ease-in-out hover:animate-pulse"
          alt="모꼬지 로고"
        />
        <!-- <span id="title-bold">모꼬지</span> -->
      </a>

      <div class="ml-auto mr-10 self-center">
        <ul class="font-medium flex md:flex-row ml-10">
          <li>
            <button id="button-header"><a href="/">HOME</a></button>
          </li>
          <li v-if="!isLogin">
            <button id="button-header" @click="toggleModal">로그인</button>
            <ModalView
              v-if="showLoginModal"
              :show-modal="showLoginModal"
              @close-modal="toggleModal"
            >
              <LoginModal />
            </ModalView>
            <button id="button-header"><a href="/signup">회원가입</a></button>
          </li>
          <li v-else>
            <button
              id="button-header"
              data-dropdown-toggle="dropdownHover"
              data-dropdown-trigger="hover"
              to="/mypage"
            >
              내 서비스
            </button>
            <div
              id="dropdownHover"
              class="z-10 hidden bg-white divide-y divide-slate-200 rounded-lg w-32"
            >
              <ul aria-labelledby="dropdownHoverButton" class="w-50">
                <li id="li-dropdown">
                  <a href="mypage">마이페이지</a>
                </li>
                <li id="li-dropdown">
                  <a href="eventlist">내 결과물</a>
                </li>
                <li id="li-dropdown">
                  <a @click="logout">로그아웃</a>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { initFlowbite } from 'flowbite'

import ModalView from '@/views/ModalView.vue'
import LoginModal from '@/components/modal/LoginModal.vue'

onMounted(() => {
  initFlowbite()
})
let isLogin = ref(true)

let showLoginModal = ref(false)

let toggleModal = () => {
  showLoginModal.value = !showLoginModal.value
}

let logout = () => {
  //sessionStorage.delete('login-token')
  isLogin.value = false
}
</script>

<style scoped></style>
