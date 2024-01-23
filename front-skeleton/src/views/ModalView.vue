<template>
  <Transition name="modal-outer">
    <div
      v-show="showModal"
      class="fixed w-full bg-black bg-opacity-30 h-screen top-0 left-0 z-10 flex justify-center px-8"
    >
      <Transition name="modal-inner">
        <div v-if="showModal" class="p-4 bg-white self-start mt-32 max-w-screen-md">
          <!-- <img src="@/icons/close.png" /> -->
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            width="24"
            height="24"
            fill="gray"
            class="m-1 float-right"
            @click="$emit('close-modal')"
          >
            <path
              d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"
            />
          </svg>
          <br />
          <slot />
        </div>
      </Transition>
    </div>
  </Transition>
</template>

<script setup>
defineProps({
  showModal: {
    type: Boolean,
    default: false
  }
})
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
