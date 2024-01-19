<template>
  <section id="main-landing">
    <main class="flex-1">
      <section class="w-full px-4 py-6 sm:py-12 md:py-24 lg:py-32 xl:py-48">
        <div id="container">
          <div class="grid gap-6 lg:grid-cols-[1fr_400px] lg:gap-12 xl:grid-cols-[1fr_600px]">
            <div class="flex flex-col justify-center space-y-4">
              <div class="space-y-5">
                <h1 id="title-bold">모꼬지</h1>
                <br />
                <p id="p-main">
                  화상 모임 플랫폼 ‘모꼬지’를 통해 결혼식, 졸업식, 돌잔치 등 다양한 행사를 공간적인
                  제약 없이 참여해보세요.
                </p>
                <p id="p-main">
                  순간을 더욱 특별하게 추억하기 위한 롤링페이퍼, 포토 모자이크 기능을 제공합니다.
                </p>
                <br />
              </div>
              <!-- TODO : 화면 가운데 div 박스 배치해서 회의 생성 버튼 및 input 을 넣기-->
              <div class="grid place-content-center">
                <router-link to="#" id="button-submit" class=""> 회의 생성하기 </router-link>
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
                  </div>
                </div>
                <p v-if="ifInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- first -->
      <section>
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2">
          <div class="mx-5 mx-5 px-5">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            
            <p id="p-main">
              모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요. 참여자는 모임중
              언제나 텍스트, 음성, 영상 메시지를 기록할 수있습니다.
            </p>
          </div>
          <div class="flex-col">
            <img src="@/assets/main1.png"/>
          </div>
        </div>
      </section>
       <!-- second -->
       <section class="bg-gray">
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2">
          <div class="flex-col">
            <img src="@/assets/main2.png"/>
          </div>
          <div class="mx-5 mx-5 px-5">
            <h1 id="title-sub-bold">포토 모자이크로 사진들을 한 눈에 구경하세요.</h1>
            
            <p id="p-main">
              포토 모자이크 기능은 서로 다른 사진들을 조합하여 하나의 이미지를 표현합니다.      모임 사진을 업로드하고 포토 모자이크      기술을 경험해 보세요. 
            </p>
          </div>
          
        </div>
      </section>
       <!-- third -->
       <section>
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2">
          <div class="mx-5 mx-5 px-5">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            
            <p id="p-main">
              모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요. 참여자는 모임중
              언제나 텍스트, 음성, 영상 메시지를 기록할 수있습니다.
            </p>
          </div>
          <div class="flex-col">
            <img src="@/assets/main3.png"/>
          </div>
        </div>
      </section>
       <!-- fourth -->
       <section class="bg-gray">
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2">
          <div class="flex-col">
            <img src="@/assets/main4.png"/>
          </div>
          <div class="mx-5 mx-5 px-5">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            
            <p id="p-main">
              모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요. 참여자는 모임중
              언제나 텍스트, 음성, 영상 메시지를 기록할 수있습니다.
            </p>
          </div>
          
        </div>
      </section>
      <!--logo-->
      <section>
        <div class="mx-5 mt-5 grid grid-cols-2 gap-2">
          <div class="flex-col">
            <img src="@/assets/mokkoji_logo.png" class="max-w-xs ml-16"/>
          </div>
          <div class="mx-5 mx-5 px-5">
            <h1 id="title-sub-bold">모꼬지를 사용하고 싶으신가요?</h1>
            
            <button @click="toTop">처음으로 돌아가기</button>
          </div>
          
        </div>
      </section>
    </main>
  </section>
</template>

<script setup>
// TODO : 비회원 사용자 회의 시작 시 안내 모달 띄워주기
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

let conferenceIdInput = ref('')
let ifInputError = ref(false)

let submitConferenceId = () => {
  console.log(conferenceIdInput.value)
  // id input을 백엔드 서버로 axios 전송
  // axios 반응이 정상이면 input값 id로 참가
  // 아니면 알람
  // 임시로 만든 올바른 id ='qwer'
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

let toTop = () => {
  window.scrollTo({
  top:0,
  behavior: 'smooth'
  })
}

</script>

<style scoped>
</style>
