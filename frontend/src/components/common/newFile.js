import { onMounted } from 'vue';
import { toast } from 'vue3-toastify';
import axios from 'axios';
import tokenService from '@/services/token.service';
import { naverquerycode, store, router } from './HandleCallback.vue';

onMounted(() => {
axios({
method: 'GET',
// url: "localhost:8080/api/vping"
url: import.meta.env.VITE_API_URL + import.meta.env.VITE_SERVER + '/oauth2/naver/' + naverquerycode.value,
})
.then((res) => {
const token = res.headers['authorization'];
tokenService.setLocalAccessToken(token);
tokenService.setUser(res.data);
store.name = res.data.name;
store.email = res.data.email;
store.image = res.data.image;
if (res.data.first == true) {
router.push('/signup');
} else {
await();

store.isLogin = true;
const refreshToken = res.headers['authorization-refresh'];
tokenService.setLocalRefreshToken(refreshToken);
router.push('/');
}
})
.catch((err) => {
toast(err.message);
});
});
