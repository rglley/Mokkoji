<template>
  <div class="text-3xl flex px-2 justify-center">
    member31님의 모꼬지 <IconBouquet /> <IconBou />
  </div>

  <div class="text-2xl px-2 flex">
    기억
    <p class="text-sm px-2 py-2.5">결과물 편집을 마치면 추억으로 간직할 수 있어요</p>
  </div>
  <div class="h-96 mb-5 border-slate-950 border-2 border-solid bg-violet-100 flex">
    <MemoryList v-for="memory in MemoryData" :key="memory.eventId" :memory="memory" />
  </div>
  <PageNavigation />
  <div class="text-2xl flex px-2">
    추억
    <p class="text-sm px-2 py-2.5">행복했던 그날로 돌아가 볼가요?</p>
  </div>
  <div class="h-96 mb-5 border-slate-950 border-2 border-solid bg-violet-100 flex">
    <RecollectionList
      v-for="recollection in RecollectionData"
      :key="recollection.eventId"
      :recollection="recollection"
    />
  </div>
  <PageNavigation />
</template>

<script setup>
import IconBouquet from '@/icons/IconBouquet.vue'
import IconBou from '@/icons/IconBou.vue'
import MemoryList from '@/components/myevent/MemoryList.vue'
import MemoryData from '@/temp/memory.json'
import RecollectionData from '@/temp/recollection.json'
import PageNavigation from '@/components/myevent/PageNavigation.vue'
import RecollectionList from '@/components/myevent/RecollectionList.vue'
</script>
<style scoped></style>
