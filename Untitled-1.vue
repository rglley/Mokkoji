<template>
  <section class="w-full py-12 md:py-24 lg:py-32">
    <header class="flex items-center justify-between px-4 lg:px-6 h-14">
      <router-link to="#" class="flex items-center justify-center">
        <TextIcon class="h-6 w-6" />
        <span class="ml-2 text-xl font-bold">Discord Community</span>
      </router-link>
      <nav class="ml-auto flex gap-4 sm:gap-6">
        <router-link to="#" class="text-sm font-medium hover:underline underline-offset-4">General</router-link>
        <router-link to="#" class="text-sm font-medium hover:underline underline-offset-4">Gaming</router-link>
        <router-link to="#" class="text-sm font-medium hover:underline underline-offset-4">Tech Talk</router-link>
        <router-link to="#" class="text-sm font-medium hover:underline underline-offset-4">Events</router-link>
      </nav>
    </header>
    <main class="flex-1">
      <section class="w-full py-6 sm:py-12 md:py-24 lg:py-32 xl:py-48">
        <div class="container px-4 md:px-6">
          <div class="grid gap-6 lg:grid-cols-[1fr_400px] lg:gap-12 xl:grid-cols-[1fr_600px]">
            <div class="flex flex-col justify-center space-y-4">
              <div class="space-y-2">
                <h1 class="text-3xl font-bold tracking-tighter sm:text-5xl xl:text-6xl/none">
                  Join Our Discord Community
                </h1>
                <p class="max-w-[600px] text-gray-500 md:text-xl dark:text-gray-400">
                  Connect with like-minded individuals, share your interests, and participate in exciting events.
                </p>
              </div>
              <div class="flex flex-col gap-2 min-[400px] flex-row">
                <router-link
                  to="#"
                  class="inline-flex h-10 items-center justify-center rounded-md bg-gray-900 px-8 text-sm font-medium text-gray-50 shadow transition-colors hover:bg-gray-900/90 focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-gray-950 disabled:pointer-events-none disabled:opacity-50 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus-visible:ring-gray-300"
                >
                  Join Now
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section class="w-full py-12 md:py-24 lg:py-32 bg-gray-100 dark:bg-gray-800">
        <div class="container px-4 md:px-6">
          <div class="flex flex-col items-center justify-center space-y-4 text-center">
            <div class="space-y-2">
              <div class="inline-block rounded-lg bg-gray-100 px-3 py-1 text-sm dark:bg-gray-800">
                Featured Events
              </div>
              <h2 class="text-3xl font-bold tracking-tighter sm:text-5xl">Don't Miss Out On Our Events</h2>
              <p class="max-w-[900px] text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed dark:text-gray-400">
                Participate in our community events and make new friends.
              </p>
            </div>
          </div>
          <div class="mx-auto grid max-w-5xl items-center gap-6 py-12 lg:grid-cols-2 lg:gap-12">
            <img
              alt="Event Image"
              class="mx-auto aspect-video overflow-hidden rounded-xl object-cover object-center sm:w-full lg:order-last"
              height="310"
              src="/placeholder.svg"
              width="550"
            />
            <div class="flex flex-col justify-center space-y-4">
              <ul class="grid gap-6">
                <li>
                  <div class="grid gap-1">
                    <h3 class="text-xl font-bold">Gaming Tournament</h3>
                    <p class="text-gray-500 dark:text-gray-400">
                      Join our monthly gaming tournament and compete with the best.
                    </p>
                  </div>
                </li>
                <li>
                  <div class="grid gap-1">
                    <h3 class="text-xl font-bold">Tech Talk</h3>
                    <p class="text-gray-500 dark:text-gray-400">
                      Participate in our weekly tech talks and learn something new.
                    </p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </main>
  </section>
</template>

<script>

</script>

<style scoped>
</style>
