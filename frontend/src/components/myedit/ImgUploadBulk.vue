<template>
  <DropZone class="h-[700px]" @files-dropped="addFiles" #default="{ dropZoneActive }">
    <label for="file-input" class="text-black">
      <span v-if="dropZoneActive">
        <span>
          <div>
            <p class="text-xl pl-80 flex text-black">
              이미지는 한 번에 &nbsp;<span class="highlight flex">24장까지 </span> &nbsp;업로드할 수
              있어요
              <IconExclamationMark />
            </p>
          </div>
        </span>
      </span>
      <span v-else class="">
        <span class="">
          <p class="text-lg flex text-black pl-40">
            업로드할 이미지들을 &nbsp; <strong>드래그 앤 드롭</strong>하세요. 이미지의 크기는 &nbsp;
            <span class="highlight flex"> 정사각형 비율 <IconCropTwo /> </span> &nbsp;로 자동
            조정됩니다.
          </p>
        </span>
        <span class="text-lg ml-[320px] text-black flex">
          또는 <strong><em>이 곳을 클릭</em></strong
          >하면 파일을 업로드 할 수 있어요. <IconSmile />
        </span>
      </span>

      <input type="file" id="file-input" multiple @change="onInputChange" class="opacity-0" />
    </label>
    <ul class="flex flex-wrap" v-show="files.length">
      <FilePreview
        v-for="file of files.slice(0, 24)"
        :key="file.id"
        :file="file"
        tag="li"
        @remove="removeFile"
      />
    </ul>
  </DropZone>
</template>

<script setup>
import useFiles from '@/api/file-list'
import DropZone from '@/components/myedit/DropZone.vue'
import FilePreview from '@/components/myedit/FilePreview.vue'
import IconExclamationMark from '@/icons/result/IconExclamationMark.vue'
import IconCropTwo from '@/icons/result/IconCropTwo.vue'
import IconSmile from '@/icons/result/IconSmile.vue'
const { files, addFiles, removeFile } = useFiles()

const onInputChange = (e) => {
  addFiles(e.target.files)
  e.target.value = null
}
</script>
<style>
.highlight {
  box-shadow: inset 0 -3px 0 #5faeb5;
}
</style>
