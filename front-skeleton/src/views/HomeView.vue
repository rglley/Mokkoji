<template>
  <section class="w-full py-12 lg:py-12">
    
    <main class="flex-1">
      <section class="w-full px-4 py-6 sm:py-12 md:py-24 lg:py-32 xl:py-48 ">
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
                <router-link
                  to="#"
                  id="conferenceJoin"
                  class="inline-flex h-10 items-center rounded-md bg-gray-900 px-8 text-sm font-medium text-white text-center transition-colors hover:bg-gray-900/90 focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-gray-950 disabled:pointer-events-none disabled:opacity-50 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus-visible:ring-gray-300"
                >
                  회의 생성하기
                </router-link>
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
            </div>
          </div>
        </div>
      </section>
      <!-- <section class="w-full py-12 md:py-24 lg:py-32 bg-gray-100 dark:bg-gray-800">
        <div class="container px-4 md:px-6">
          <div class="flex flex-col items-center justify-center space-y-4 text-center">
            <div class="space-y-2">
              <div class="inline-block rounded-lg bg-gray-100 px-3 py-1 text-sm dark:bg-gray-800">
                Featured Events
              </div>
              <h2 class="text-3xl font-bold tracking-tighter sm:text-5xl">
                Don't Miss Out On Our Events
              </h2>
              <p
                class="max-w-[900px] text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed dark:text-gray-400"
              >
                Participate in our community events and make new friends.
              </p>
            </div>
          </div>
          <div class="mx-auto grid max-w-5xl items-center gap-6 py-12 lg:grid-cols-2 lg:gap-12">
            <img
              alt="Event Image"
              class="mx-auto aspect-video overflow-hidden rounded-xl object-cover object-center sm:w-full lg:order-last"
              height="310"
              src=""
              width="550"
            />
            <div class="flex flex-col justify-center space-y-4">
              <ul class="grid gap-6">
                <li>
                  <div class="grid gap-1">
                    <h3 class="text-xl font-bold">Gaming Tournament</h3>
                    <p class="text-gray-500 dark:text-gray-400">
                      Join our monthly gaming tournament and compete with the best.
                    </p>
                  </div>
                </li>
                <li>
                  <div class="grid gap-1">
                    <h3 class="text-xl font-bold">Tech Talk</h3>
                    <p class="text-gray-500 dark:text-gray-400">
                      Participate in our weekly tech talks and learn something new.
                    </p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </section> -->
    </main>
  </section>
</template>

<script setup>
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
</script>

<style scoped>
</style>
