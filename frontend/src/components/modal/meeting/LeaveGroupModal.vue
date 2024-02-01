<template>
  <div
    class="fixed h-[15vh] aspect-[1.3] bottom-[11%] left-[78.5vw] flex flex-col justify-center items-center bg-red-100 rounded-r-lg"
  >
    <div class="w-full h-1/2 flex items-center border-b-[0.1vb] border-zinc-400">
      <button
        class="w-full h-full border-sm font-bold text-r-sm"
        @click="$emit('leave-group-meeting')"
      >
        소그룹 나가기
      </button>
    </div>
    <div class="w-full h-1/2 flex items-center">
      <button class="w-full h-full font-bold text-r-sm" @click="$emit('leave-meeting')">
        회의 종료
      </button>
    </div>
  </div>
</template>

<script setup>
defineEmits(['leave-meeting']['leave-group-meeting'])
</script>

<style>
.bg-red-200 {
  background-color: #f9dcda;
}
</style>
