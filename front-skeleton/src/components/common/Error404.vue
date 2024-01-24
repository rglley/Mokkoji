<template>
  <div class="flex items-center justify-center w-full min-h-[70vh] text-gray-900 my-12 px-4">
    <div class="flex flex-col items-center w-full gap-8">
      <h1 class="text-9xl md:text-16xl w-full select-none text-center font-black text-gray-400">
        404
      </h1>
      <p class="text-3xl font-semibold text-center">페이지를 찾을 수 없어요.</p>
      <p class="text-2xl md:px-12 text-center">
        존재하지 않는 페이지를 입력하셨거나, 요청하신 페이지가 변경 또는 이동했어요.
      </p>
      <div class="flex flex-row justify-between gap-8">
        <a
          @click="$router.go(-1)"
          class="flex justiy-center items-center px-5 py-2 text-xl rounded-md text-black border border-indigo-500 hover:bg-indigo-500 hover:text-white"
        >
          전 페이지로 이동
        </a>
        <a
          href="/"
          class="flex justiy-center items-center px-5 py-2 text-xl rounded-md text-black border border-green-500 hover:bg-green-500 hover:text-white"
        >
          홈으로
        </a>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>