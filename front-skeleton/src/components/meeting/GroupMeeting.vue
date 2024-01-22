<template>
  <section class="w-full h-full overflow-x-scroll overflow-hidden">
    <div class="w-full h-full flex flex-nowrap gap-2">
      <div
        v-for="participant in participants"
        :key="participant"
        class="w-1/2 h-1/2 flex-none bg-purple-200 rounded-xl p-3"
      >
        {{ participant }}
      </div>
    </div>
  </section>
</template>

<script setup></script>

<style></style>
