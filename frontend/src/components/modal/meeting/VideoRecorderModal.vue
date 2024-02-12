<template>
  <div
    class="absolute self-center ml-1/2 mt-1/2 p-[4lvh] bg-purple-200 rounded-r-xl flex flex-col gap-1 items-center w-fit h-fit z-20"
  >
    <video
      ref="videoRef"
      width="200"
      autoplay
      class="border-2 border-white rounded-lg"
    ></video>
    <div class="flex flex-row justify-center h-10">
      <button @click="startRecording" :disabled="isRecording">
        <IconRecord class="size-[100%] fill-red-500"></IconRecord>
      </button>
      <button @click="stopRecording" :disabled="!isRecording">
        <IconStop class="size-[100%] fill-red-500" />
      </button>
      <!-- <button @click="downloadRecording" :disabled="!videoFile">Download Recording</button> -->
    </div>
    <video
      v-if="isEndRecording"
      ref="recordedVideo"
      width="150lwh"
      controls
      :src="recordedVideoSrc"
    ></video>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import IconRecord from "@/icons/meeting/IconRecord.vue";
import IconStop from "@/icons/meeting/IconStop.vue";
import IconCancelBlack from "@/icons/meeting/IconCancelBlack.vue";

defineEmits(["show-video-recorder"]);

const videoRef = ref(null);
const stream = ref(null);
const isRecording = ref(false);
const isEndRecording = ref(false);
const mediaRecorder = ref(null);
const chunks = ref([]);
const videoFile = ref(null);
const recordedVideoSrc = ref("");

async function startCamera() {
  try {
    stream.value = await navigator.mediaDevices.getUserMedia({ video: true });
    videoRef.value.srcObject = stream.value;
  } catch (error) {
    console.error("Error accessing camera:", error);
  }
}

function startRecording() {
  if (isEndRecording.value) startCamera();

  if (!stream.value || !(stream.value instanceof MediaStream)) {
    console.error('Invalid stream for recording');
    return;
  }

  try {
    mediaRecorder.value = new MediaRecorder(stream.value);
    chunks.value = [];
    mediaRecorder.value.ondataavailable = event => {
      if (event.data.size > 0) {
        chunks.value.push(event.data);
      }
    };
    mediaRecorder.value.onstop = () => {
      const recordedBlob = new Blob(chunks.value, { type: "video/webm" });
      videoFile.value = recordedBlob;
      isRecording.value = false;
      videoRef.value.srcObject = null;
      recordedVideoSrc.value = URL.createObjectURL(recordedBlob);
    };
    mediaRecorder.value.start();
    isRecording.value = true;
  } catch (error) {
    console.error("Error starting recording:", error);
  }
}

function stopRecording() {
  if (mediaRecorder.value && isRecording.value) {
    mediaRecorder.value.stop();
    isEndRecording.value = true;
  }
}

function downloadRecording() {
  if (videoFile.value) {
    const url = URL.createObjectURL(videoFile.value);
    const a = document.createElement("a");
    a.style.display = "none";
    a.href = url;
    a.download = "recorded_video.webm";
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
  }
}

onMounted(() => {
  startCamera();
});
</script>

<style scoped></style>
