<template>
  <div
    v-for="chatMessage in props.chatLog"
    :key="chatMessage"
    class="bg-white w-[90%] min-h-16 rounded-lg text-base flex justify-center items-center font-semibold"
  >
    {{ chatMessage }}
  </div>
</template>

<script setup>
const props = defineProps({
  chatLog: {
    type: Array
  }
})
</script>

<style></style>
