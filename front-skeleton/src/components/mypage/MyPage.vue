<template>
  <div
    class="bg-white flex mt-0 w-full flex-col justify-center items-center px-16 py-12 max-md:max-w-full max-md:px-5"
  >
    <div
      class="flex w-[821px] max-w-full flex-col mt-24 mb-40 items-start max-md:my-10 bg-primary0"
    >
      <div
        class="border bg-purple-50 self-stretch px-20 py-10 rounded-3xl border-solid border-black max-md:max-w-full max-md:px-5"
      >
        <div class="gap-5 flex max-md:flex-col max-md:items-stretch max-md:gap-0">
          <div class="flex flex-col items-stretch w-6/12 max-md:w-full max-md:ml-0">
            <img
              alt="프로필 사진"
              class="overflow-hidden rounded-full border-4 border-white dark:border-gray"
              :height="256"
              src="@/assets/dummy_profile.jpg"
              style="object-fit: fill"
              :width="256"
            />
            <span class="flex flex-col mt-10 max-md:mt-10"
              ><div class="text-black text-3xl font-bold self-stretch">{{ store.user.id }}님</div>
              <div class="text-black text-xl self-stretch mt-2.5">{{ store.user.email }}</div>
              <span
                class="self-stretch flex items-stretch justify-between gap-4 mt-8 pr-4"
                ><div class="text-black text-3xl">
                  계좌 등록
                  <a class="text-3xl text-red-500" v-if="store.user.isAccountLinked">O</a>
                  <a class="text-3xl text-red-500" v-else>X </a>
                </div>
              </span>
              <div class="marker:text-xs">
                계좌를 등록하시면 참가자들의 마음을 받을 수 있어요
              </div>
              <br>
              <button class="rounded-full">회원정보 수정</button>
            </span>
          </div>
          <div class="flex flex-col justify-end items-stretch w-1/2 ml-5">
            <div>
              <span class="flex items-stretch justify-between gap-0"
                >
                <div class="text-black text-2xl font-bold">활동 기록</div>
                <a href="#" class="text-1xl">상세보기 ></a>
              
              </span>
            </div>
            <div class="flex items-stretch justify-between gap-5 mt-6 pr-1.5">
              <div
                class="flex-col border-solid border-2 border-purple-700 rounded-md  bg-white text-center text-xl font-bold relative overflow-hidden  w-[160px] justify-center px-7 py-10"
              >
                모꼬지 주최<br /><span class="text-purple-400">{{ store.eventRecord.eventTotalCnt }}</span>번<br />
              </div>
              <div
              class="flex-col border-solid border-2 border-purple-700 rounded-md  bg-white text-center text-xl font-bold relative overflow-hidden  w-[160px] justify-center px-7 py-10"
              >
                참여자 수<br /><span class="text-purple-400">{{ store.eventRecord.eventPplCnt }}</span>명
              </div>
            </div>
            <div class="flex items-stretch justify-between gap-5 mt-6 pr-1.5">
              <div
              class="flex-col border-solid border-2 border-purple-700 rounded-md  bg-white text-center text-xl font-bold relative overflow-hidden  w-[160px] justify-center px-7 py-10"
              >
                모꼬지 시간<br /><span class="text-purple-400">{{ eventHour }}</span>시간
                <span class="text-purple-400">{{ eventMinute }}</span>분
              </div>
              <div
              class="flex-col border-solid border-2 border-purple-700 rounded-md  bg-white text-center text-xl font-bold relative overflow-hidden  w-[160px] justify-center px-7 py-10"
              >
                받은 메세지<br /><span class="text-purple-400">{{ store.eventRecord.eventMsgCnt }}</span>개
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from "vue";
import { useUserStore } from "../../stores/user";

const store = useUserStore();
const eventHour = ref(0);
const eventMinute = ref(0);
// 로딩 시
onMounted(() => {
  eventHour.value = computed(() => {
    return parseInt(store.eventRecord.eventTotalMinute / 60);
  });
  eventMinute.value = computed(() => {
    return store.eventRecord.eventTotalMinute % 60;
  });
});
</script>

<style></style>
