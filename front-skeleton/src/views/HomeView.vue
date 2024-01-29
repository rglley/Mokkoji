<template>
  <!-- <section class="carousel" aria-label="Gallery">
    <ol class="carousel__viewport">
      <li id="carousel__slide1" tabindex="0" class="carousel__slide">
        <div class="carousel__snapper"></div>
      </li>
      <li id="carousel__slide2" tabindex="0" class="carousel__slide">
        <div class="carousel__snapper"></div>
      </li>
      <li id="carousel__slide3" tabindex="0" class="carousel__slide">
        <div class="carousel__snapper"></div>
      </li>
    </ol>
  </section> -->
  <main id="main-landing">
    <!-- <section class="pb-12 pt-32 bg-[url('/src/assets/main_banner2.png')] mb-10"> -->
    <section class="pt-52 z-2 h-max" id="main-gradient2">
      <div class="absolute y-0 right-0">
        <img src="@/assets/wedding.svg" />
      </div>
      <div id="container" class="mt-52 flex flex-col">
        <div
          class="text-justify ml-12 flex flex-col w-2/3 animate-in fade-in-10 delay-1000 duration-1000"
        >
          <!-- <div class="text-center"> -->
          <h1 id="title-bold" class="ml-8 text-8xl">모꼬지</h1>
          <br />
          <p>
            화상 모임 플랫폼 ‘모꼬지’를 통해 결혼식, 졸업식, 돌잔치 등 다양한 행사를
            공간적인 제약 없이 참여해보세요.
          </p>
          <p>
            순간을 더욱 특별하게 추억하기 위한 롤링페이퍼, 포토 모자이크 기능을
            제공합니다.
          </p>
          <!-- TODO : 화면 가운데 div 박스 배치해서 회의 생성 버튼 및 input 을 넣기-->
          <div class="grid place-content-center my-20 w-2/3">
            <button class="rounded-2xl" @click="generateMeeting">
              화상 모임 생성하기
            </button>

            <!-- TODO : submit 버튼 + invalid input 렌더링 -->
            <div>
              <div class="relative">
                <input
                  type="text"
                  placeholder="회의 링크(ID)로 참여하기"
                  v-model="conferenceIdInput"
                  @keyup.enter="submitConferenceId"
                  class="pl-10 w-60 border-2 border-slate-500 rounded-xl"
                />
                <div class="absolute top-2 right-2">
                  <button @click="submitConferenceId" class="rounded-full size-8 mt-2">
                    <img src="@/icons/send.png" class="" />
                  </button>
                </div>
                <ModalView
                  v-if="showModal"
                  :show-modal="showModal"
                  @close-modal="toggleModal"
                >
                  <MeetingJoinModal :conferenceIdInput="conferenceIdInput" />
                </ModalView>
              </div>
              <p v-if="ifInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
            </div>
            <p v-if="ifInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
          </div>
        </div>
      </div>
    </section>

    <!-- first -->
    <section id="section-main">
      <div class="w-full overflow-hidden">
        <svg
          id="svg-curve-1"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="mx-5 px-5 text-center">
          <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
          <br />
          <p id="p-main">
            모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.
          </p>
          <p id="p-main">
            참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
          </p>
        </div>
        <div class="items-center">
          <img src="@/assets/main1.png" />
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!-- second -->
    <section id="section-main" class="bg-natural-yellow">
      <div class="w-full overflow-hidden">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
          id="svg-curve-2"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
            class="shape-fill"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="justify-self-end mr-20">
          <img class="pl-0" src="@/assets/main2.png" />
        </div>
        <div class="text-center">
          <h2 id="title-sub-bold">포토 모자이크로 사진들을 한 눈에 구경하세요.</h2>
          <br />
          <p id="p-main">
            포토 모자이크 기능은 서로 다른 사진들을 조합하여 하나의 이미지를 표현합니다.
          </p>
          <p id="p-main">모임 사진을 업로드하고 포토 모자이크 기술을 경험해 보세요.</p>
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!-- third -->
    <section id="section-main" class="bg-natural-beige">
      <div class="w-full overflow-hidden">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
          id="svg-curve-3"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
            class="shape-fill"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="mx-5 text-center">
          <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
          <br />
          <p id="p-main">
            모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.
          </p>
          <p id="p-main">
            참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
          </p>
        </div>
        <div class="flex-col items-center">
          <img src="@/assets/main3.png" />
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!-- fourth -->
    <section id="section-main" class="bg-primary2">
      <div class="w-full overflow-hidden">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
          id="svg-curve-4"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
            class="shape-fill"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="justify-self-end mr-20">
          <img src="@/assets/main4.png" />
        </div>
        <div class="mx-5 px-5 text-center">
          <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
          <br />
          <p id="p-main">
            모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.
          </p>
          <p id="p-main">
            참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
          </p>
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!--logo-->
    <section class="flex items-center justify-center py-20 bg-primary2">
        <h1 id="title-sub-bold" class="">모꼬지를 사용하고 싶으신가요?</h1>
        <button @click="toTop" class="animate-spin">처음으로 돌아가기</button>
    </section>
  </main>
</template>

<script setup>
// TODO : 비회원 사용자 회의 시작 시 안내 모달 띄워주기
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

// toast : custom alert
import { toast } from "vue3-toastify";
import "vue3-toastify/dist/index.css";

import ModalView from "./ModalView.vue";
import MeetingJoinModal from "../components/modal/MeetingJoinModal.vue";

let conferenceIdInput = ref("");
let ifInputError = ref(false);
let isLogin = ref(false); // 나중에 store에서 받을 예정

let showModal = ref(false);

const toggleModal = () => {
  showModal.value = !showModal.value;
};

let submitConferenceId = () => {
  console.log(conferenceIdInput.value);
  // id input을 백엔드 서버로 axios 전송
  // axios 반응이 정상이면 input값 id로 참가
  // 아니면 알람
  // 임시로 만든 올바른 id ='qwer'
  let validId = "qwer";
  if (conferenceIdInput.value == validId) {
    // 로그인하지 않았다면 모달
    if (!isLogin.value) {
      // 모달 띄우고
      showModal.value = true;
    }
    ifInputError.value = false;
    // 회의 이동
    router.push("/");
  } else {
    ifInputError.value = true;
    conferenceIdInput.value = "";
  }
};

// 회의 생성 로직
// 로그인하지 않았다면 모달
// 로그인하지 않았으면 alert
let generateMeeting = () => {
  if (isLogin.value) {
    router.push("#"); // 회의 리다이렉트(예정)
  } else {
    toast("로그인이 필요합니다", {
      theme: "auto",
      type: "warning",
      transition: "flip",
      autoClose: 1000,
    });
  }
};

let toTop = () => {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
};
</script>

<style scoped></style>
