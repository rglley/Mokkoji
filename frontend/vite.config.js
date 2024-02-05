import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  optimizeDeps: {
    include: ['events']
  },
  server: {
    // 모든 네트워크 인터페이스에서 수신 대기하도록 설정
    host: '0.0.0.0'
  },
  define: {
    global: {}
  }
})
