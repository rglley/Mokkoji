<template>
  <div class="flex">
    <div class="flex m-auto">
      <select
        id="designDropdown"
        v-model="selectedDesign"
        class="w-48 border-2 border-solid border-gray-300 rounded-lg h-12 pl-2 mr-2"
      >
        <option value="" selected disabled hidden>템플릿 디자인 선택</option>
        <option v-for="design in designs" :key="design">{{ design }}</option>
      </select>
      <button class="border-4 border-solid border-violet-500 rounded-lg px-3 py-1 my-1">
        편집 완료
      </button>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
let selectedDesign = ref('')
let designs = ['기본', '결혼식', '졸업식', '명절', '돌잔치']
</script>
<style scoped></style>
