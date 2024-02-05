import { defineStore } from 'pinia'

export const mainImageStore = defineStore('mainImg', {
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
