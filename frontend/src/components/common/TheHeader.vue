<template>
  <header
    :class="{ 'transparent-header': isTransparent }"
    class="fixed h-[10vh] z-10 my-auto w-full bg-transparent"
  >
    <nav class="w-full flex items-center px-[2vw]">
      <router-link
        to="/"
        class="w-[8vw] flex items-center rtl:l:space-x-reverse cursor-grab"
      >
        <img
          src="/src/assets/logo/mokkoji_logo.png"
          class="w-[8vw] transition ease-in-out hover:animate-pulse"
          alt="모꼬지 로고"
        />
      </router-link>

      <div class="ml-auto self-center">
        <ul class="font-medium flex md:flex-row">
          <li v-show="!(store.isLogin || isLogin)">
            <button
              id="button-header"
              @click="showLoginModal"
              class="text-[2.5vh] cursor-grab"
            >
              로그인
            </button>
            <ModalView
              v-if="isLoginModal"
              :show-modal="isLoginModal"
              @close-modal="showLoginModal"
            >
              <LoginModal />
            </ModalView>
          </li>
          <li v-show="store.isLogin || isLogin">
            <div
              class="flex flex-row relative justify-center items-center gap-[2.5vh] text-[2.5vh]"
            >
              <div class="flex justify-center items-center rounded-full">
                <img
                  class="overflow-hidden rounded-full w-[7vh] m-0 mr-[1vw]"
                  :src="image"
                />
                <p class="text-black text-[2.5vh]">{{ name }}님</p>
              </div>
              <button
                id="button-header"
                data-dropdown-toggle="dropdown"
                data-dropdown-trigger="click"
              >
                내 서비스
              </button>
              <button id="button-header" @click="logout">로그아웃</button>
              <div
                :key="dropdownKey"
                id="dropdown"
                class="z-10 hidden bg-white divide-y divide-slate-200 rounded-lg w-32"
              >
                <ul aria-labelledby="dropdownHoverButton" class="w-50">
                  <li id="li-dropdown" class="text-[2.5vh]">
                    <router-link to="mypage">마이페이지</router-link>
                  </li>
                  <li id="li-dropdown" class="text-[2.5vh]">
                    <router-link to="eventlist">내 결과물</router-link>
                  </li>
                </ul>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, onBeforeMount, watch } from "vue";
import { initFlowbite } from "flowbite";
import { stringifyQuery, useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
import ModalView from "@/views/ModalView.vue";
import LoginModal from "@/components/modal/home/LoginModal.vue";
import tokenService from "@/services/token.service";
import Swal from "sweetalert2";

const router = useRouter();
const store = useUserStore();
const isLoginModal = ref(false);
const isTransparent = ref(false);
const image = ref("");
const name = ref("");
const isLogin = ref(false);
const limitHeight = 200;
const dropdownKey = ref(3);

initFlowbite();

// header 홈뷰에서 새로고침
const reloadPage = () => {
  store.isReloaded = true;
  router.push("/").then(() => {
    window.location.reload();
  });
};

const showLoginModal = () => {
  isLoginModal.value = !isLoginModal.value;
};

const handleScroll = () => {
  if (scrollY > limitHeight) isTransparent.value = true;
  if (scrollY < limitHeight) isTransparent.value = false;
};

const logout = () => {
  Swal.fire({
    title: "로그아웃 하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "네",
    cancelButtonText: "돌아가기",
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire({
        title: "로그아웃 되었습니다!",
        icno: "info",
      }).then(() => {
        tokenService.removeUser();
        isLogin.value = false;
        store.isLogin = false;
      });
      router.push("/");
    }
  });
};

onBeforeMount(() => {
  window.addEventListener("scroll", handleScroll);
  // 브라우저를 재연결시 이미 쿠키에 저장된 토큰 만료 여부 처리
  if ($cookies.isKey("user")) {
    if (tokenService.expiredToken($cookies.get("token"))) {
      $cookies.keys().forEach((cookie) => $cookies.remove(cookie));
    } else {
      isLogin.value = true;
      image.value = $cookies.get("user").image;
      name.value = $cookies.get("user").name;
    }
  }
});

watch(
  () => store.forceReload,
  (newValue, oldValue) => {
    if (newValue === true) {
      store.forceReload = false;
      setTimeout(reloadPage, 100);
    }
  },
  () => store.isReloaded,
  (newValue, oldValue) => {
    if (newValue == true) {
      store.isReload = false;
      Swal.fire({
        icon: "success",
        title: `환영합니다, ${store.name} 님!`,
      });
    }
  }
);
</script>

<style>
.transparent-header {
  @apply opacity-0 transition-opacity duration-500 z-10 pointer-events-none;
}

li {
  @apply m-[1vw] p-[1vw];
}
</style>
