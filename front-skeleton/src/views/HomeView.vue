<template>
<<<<<<< HEAD
  <section class="w-full py-12 lg:py-12">
    <main class="flex-1">
      <section class="w-full px-4 py-6 sm:py-12 md:py-24 lg:py-32 xl:py-48">
        <div class="container px-4 md:px-6">
          <div class="grid gap-6 lg:grid-cols-[1fr_400px] lg:gap-12 xl:grid-cols-[1fr_600px]">
            <div class="flex flex-col justify-center space-y-4">
              <div class="space-y-5">
                <h1 class="text-3xl font-bold tracking-tighter sm:text-5xl xl:text-6xl/none">
                  모꼬지
                </h1>
                <p class="max-w-[600px] text-gray-500 md:text-xl dark:text-gray-400">
                  화상 모임 플랫폼 ‘모꼬지’를 통해 결혼식, 졸업식, 돌잔치 등 다양한 행사를 공간적인
                  제약 없이 참여해보세요.
                </p>
                <p class="max-w-[600px] text-gray-500 md:text-xl dark:text-gray-400">
                  순간을 더욱 특별하게 추억하기 위한 롤링페이퍼, 포토 모자이크 기능을 제공합니다.
                </p>
              </div>
              <div class="flex flex-col gap-2 min-[400px] flex-row">
                <!-- TODO : submit 버튼 + invalid input 렌더링 -->

                <div class="flex items-center">
                  <input
                    type="text"
                    placeholder="회의 ID를 붙여넣으세요"
                    id="conferenceId"
                    class="inline-flex h-10 items-center justify-center rounded-md bg-gray-900 px-8 text-sm font-medium text-gray-50 shadow transition-colors hover:bg-gray-900/90 focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-gray-950 disabled:pointer-events-none disabled:opacity-50 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus-visible:ring-gray-300"
                    v-model="conferenceIdInput"
                    @keyup.enter="submitConferenceId"
                  />
                  <button
                    class="ml-2 inline-flex h-10 items-center justify-center rounded-md bg-blue-500 px-6 text-sm font-medium text-white shadow transition-colors hover:bg-blue-600 focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-blue-600"
                    @click="submitConferenceId"
                  >
                    Submit
                  </button>
                </div>
                <p v-if="ifInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
              </div>
=======
  <section id="main-landing">
    <main>
      <section class="pb-12 pt-32 bg-[url('/src/assets/main_banner2.png')] mb-10">
        <div id="container">
          <div class="text-center">
            <h1 id="title-bold">‘모꼬지 (Mokkoji)’</h1>
            <p id="title-sub-bold"> 놀이나 잔치로 여러 사람이 모이는  것</p>
            <br />
            <p id="p-main">
              화상 모임 플랫폼 ‘모꼬지’를 통해 결혼식, 졸업식, 돌잔치 등 다양한 행사를
              공간적인 제약 없이 참여해보세요.
            </p>
            <p id="p-main">
              순간을 더욱 특별하게 추억하기 위한 롤링페이퍼, 포토 모자이크 기능을
              제공합니다.
            </p>
            <br>
            <!-- TODO : 화면 가운데 div 박스 배치해서 회의 생성 버튼 및 input 을 넣기-->
            <div class="grid place-content-center">
              <button id="button" @click="generateMeeting">회의 생성하기</button>
              <!-- TODO : submit 버튼 + invalid input 렌더링 -->
              <div class="grid place-content-center">
                <div>
                  <input
                    type="text"
                    placeholder="회의 ID를 붙여넣으세요"
                    v-model="conferenceIdInput"
                    @keyup.enter="submitConferenceId"
                  />
                  <button @click="submitConferenceId" id="button-submit">Submit</button>
                  <!-- <div v-if="showModal" tabindex="-1" class="fixed top-80 bg-primary right-0 left-0 z-50 justify-center items-center">
                    <p>qweqew</p>
                  </div> -->
                </div>
              </div>
              <p v-if="ifInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
>>>>>>> 1faa21a75d614805a9043ea9e6f25cd634d792ae
            </div>
          </div>
        </div>
      </section>
<<<<<<< HEAD
=======

      <!-- first -->
      <section class="my-16 p-16">
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="mx-5 mx-5 px-5 text-center">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
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
      </section>

      <!-- second -->
      <section class="my-16 p-16 bg-gray">
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="justify-self-end mr-20">
            <img class="pl-0" src="@/assets/main2.png" />
          </div>
          <div class="text-center">
            <h1 id="title-sub-bold">포토 모자이크로 사진들을 한 눈에 구경하세요.</h1>
            <p id="p-main">
              포토 모자이크 기능은 서로 다른 사진들을 조합하여 하나의 이미지를 표현합니다.
            </p>
            <p id="p-main">모임 사진을 업로드하고 포토 모자이크 기술을 경험해 보세요.</p>
          </div>
        </div>
      </section>

      <!-- third -->
      <section class="my-16 p-16">
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="mx-5 mx-5 px-5 text-center">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
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
      </section>

      <!-- fourth -->
      <section class="my-16 p-16 bg-gray">
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="justify-self-end mr-20">
            <img src="@/assets/main4.png" />
          </div>
          <div class="mx-5 mx-5 px-5 text-center">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            <p id="p-main">
              모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.
            </p>
            <p id="p-main">
              참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
            </p>
          </div>
        </div>
      </section>

      <!--logo-->
      <section>
        <div class="grid grid-cols-2 gap-2 items-center">
          <div class="justify-self-end mr-20">
            <img src="@/assets/mokkoji_logo.png" class="max-w-xs" />
          </div>
          <div class="mx-5 mx-5 px-5 ml-10">
            <h1 id="title-sub-bold">모꼬지를 사용하고 싶으신가요?</h1>
            <button @click="toTop">처음으로 돌아가기</button>
          </div>
        </div>
      </section>
>>>>>>> 1faa21a75d614805a9043ea9e6f25cd634d792ae
    </main>
  </section>
</template>

<script setup>
<<<<<<< HEAD
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
const router = useRouter()

axios.defaults.headers.post['Content-Type'] = 'application/json'

let conferenceIdInput = ref('')
let ifInputError = ref(false)

let submitConferenceId = () => {
  console.log(conferenceIdInput.value)
=======
// TODO : 비회원 사용자 회의 시작 시 안내 모달 띄워주기
import { ref} from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

let conferenceIdInput = ref("");
let ifInputError = ref(false);
let isLogin = ref(false); // 나중에 store에서 받을 예정
let showModal = ref(false);

let submitConferenceId = () => {
  console.log(conferenceIdInput.value);
>>>>>>> 1faa21a75d614805a9043ea9e6f25cd634d792ae
  // id input을 백엔드 서버로 axios 전송
  // axios 반응이 정상이면 input값 id로 참가
  // 아니면 알람
  // 임시로 만든 올바른 id ='qwer'
<<<<<<< HEAD
  let validId = 'qwer'
  if (conferenceIdInput.value == validId) {
    // 방 이동
    alert('성공')
    conferenceIdInput.value = ''
    // 회의 이동
    router.push('/')
  } else {
    ifInputError.value = true
    conferenceIdInput.value = ''
  }
}
=======
  let validId = "qwer";
  if (conferenceIdInput.value == validId) {
    // 로그인하지 않았다면 모달
    if (!isLogin.value) {
      // 모달 띄우고
      showModal.value = true;
      // 회원가입을 누르면 회원가입 리다이렉트

      // 알겠어요 누르면 아이디 입력 받고

      // 회의 이동
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
    alert("로그인이 필요합니다");
  }
};

let toTop = () => {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
};
>>>>>>> 1faa21a75d614805a9043ea9e6f25cd634d792ae
</script>

<style scoped></style>
