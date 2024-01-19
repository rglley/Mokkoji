<template>
  <div
    class="grid grid-cols-2 gap-4 p-50 m-10 max-w-screen-md border-black justify-around place-items-center"
  >
    <div class="row-span-1 bg-gray">
      <div>
        <img
          alt="프로필 사진"
          class="overflow-hidden rounded-full border-4 border-white dark:border-gray"
          :height="256"
          src="@/assets/dummy_profile.jpg"
          style="object-fit: fill"
          :width="256"
        />
      </div>
      <div>
        <h1 id="title-sub-bold">{{ store.user.id }}님</h1>
        <p id="p-main">{{ store.user.email }}</p>
        <!-- TODO : 계좌 연동 따른 조건부 렌더링 des-->
        <div>
          <div v-if="store.user.isAccountLinked">
            <h4 id="title=bold">계좌 연동 했어용</h4></div>
          <div v-else>
            <h4 id="title=bold">계좌 연동 하세요!<a href="#">계좌 연동 하러가기</a> </h4>
            <p>계좌를 등록하시면 참가자들의 마음을 받을 수 있어요</p> 
            </div>
        </div>
        <router-link to="/mypage/detail" id="button-submit">회원 정보 수정</router-link>
      </div>
    </div>
    <!-- TODO : stat visualization-->
    <div class="row-span-2 bg-gray w-max">
      <h1 id="title-sub-bold">기록</h1>
      <p id="p-main">{{ store.eventRecord.eventTotalCnt }}</p>
      <p id="p-main">{{ eventHour }} 시간 {{ eventMinute }} 분</p>
      <p id="p-main">참가자 {{ store.eventRecord.eventPplCnt }}명</p>
      <p id="p-main">메시지 {{ store.eventRecord.eventMsgCnt }}개</p>

    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted, computed } from 'vue'
import { useUserStore } from '../../stores/user'

const store = useUserStore()
const eventHour = ref(0)
const eventMinute = ref(0)
// 로딩 시
onMounted(() => {
  eventHour.value = computed(() => {
    return parseInt(store.eventRecord.eventTotalMinute/60);
  })
  eventMinute.value = computed(() => {
    return store.eventRecord.eventTotalMinute % 60;
  })
})


</script>
  
<style>
</style>
  