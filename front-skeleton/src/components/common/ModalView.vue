<template>
  <Transition name="modal-outer">
    <div
      v-show="showLoginModal"
      class="absolute w-full bg-black bg-opacity-30 h-screen top-0 left-0 flex justify-center px-8"
    >
      <Transition name="modal-inner">
        <div v-if="showLoginModal" class="p-4 bg-white self-start mt-32 max-w-screen-md">
          <slot />
          <button @click="$emit('close-modal')">닫기</button>
        </div>
      </Transition>
    </div>
  </Transition>
</template>
  
<script setup>
defineProps({
  showLoginModal: {
    type: Boolean,
    default: false
  }
})

defineEmits('close-modal')
</script>
  
<style scoped>
.modal-outer-enter-active,
.modal-outer-inner-active {
  transition: opacity 0.5s cubic-bezier(0.55, 0.055, 0.675, 0.19);
}

.modal-outer-enter-from,
.modal-outer-enter-to,
.modal-inner-enter-to,
.modal-inner-enter-to {
  transition: all 0.3s cubic-bezier(0.55, 0.055, 0.675, 0.19);
}
</style>