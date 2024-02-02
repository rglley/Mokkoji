<template>
  <div
    class="flex items-center justify-center w-full h-screen text-gray-900 px-4"
    id="main-gradient3"
  >
    <div
      class="flex flex-col justify-center items-center w-full gap-8 h-3/4 mx-48 bg-white rounded-3xl shadow-md border-2 border-yellow-100"
    >
      <p class="text-3xl font-semibold text-center">페이지를 찾을 수 없어요.</p>
      <p class="text-2xl md:px-12 text-center">
        존재하지 않는 페이지를 입력하셨거나, 요청하신 페이지가 변경 또는 이동했어요.
      </p>
      <div class="flex flex-row justify-between gap-8">
        <a
          @click="$router.go(-1)"
          class="flex justiy-center items-center px-5 py-2 text-xl rounded-md text-black border border-indigo-500 hover:bg-indigo-500 hover:text-white"
        >
          이전 페이지로 이동
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

<script></script>
