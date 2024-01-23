import '../src/style.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
//scroll animation
import AOS from 'aos'
import 'aos/dist/aos.css'
AOS.init()
//google login
import vue3GoogleLogin from 'vue3-google-login'

const CLIENT_ID = '915718363320-jbd48s0k9jo96h5vb5djcn93tnpmn803.apps.googleusercontent.com'

import App from './App.vue'
import router from './router'

const app = createApp(App).use(vue3GoogleLogin, {
  clientId: CLIENT_ID
})

app.use(createPinia())
app.use(router)

app.mount('#app')
