import { defineStore } from 'pinia'
import axiosJwt from '@/services/api'
import axios from 'axios'

const { VITE_API_URL_LOCAL } = import.meta.env
const { VITE_API_URL } = import.meta.env
const { VITE_SERVER } = import.meta.env

export const useMainImageStore = defineStore('mainImg', {
  state: () => ({
    mainImage: null
  }),
  actions: {
    selectedImage(image) {
      this.mainImage = image
    },

    clearSelectedImage() {
      this.mainImage = null
    }
  },
  getters: {
    getSelectedImage() {
      return this.mainImage
    }
  }
})

export const useFormDataStore = defineStore('formData', {
  state: () => ({
    formData: new FormData()
  }),
  actions: {
    addFile(file) {
      this.formData.append('file', file)
      console.log(this.formData)
    },
    clearFormData() {
      this.formData = new FormData()
    }
    // You can add more actions here as needed
  }
})

export const useImgUploadStore = defineStore('imgUpload', () => {
  const addPhotos = async (id, formData, success, fail) => {
    // console.log(id)
    // console.log(formData)
    await axiosJwt
      .post(VITE_API_URL_LOCAL + VITE_SERVER + 'results/memories/photos', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(success)
      .catch(fail)
  }

  return {
    addPhotos
  }
})
