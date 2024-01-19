import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MyPageView from '@/views/MyPageView.vue'
import MyPage from '@/components/mypage/MyPage.vue'
import MyDetail from '@/components/mypage/MyDetail.vue'
import MyAccount from '@/components/mypage/MyAccount.vue'

// import MyMeeting from '@/components/conference/MyMeeting.vue'
// import MeetingView from '@/views/MeetingView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      // path: '/${userId}',
      path: '/mypage',
      name: 'mypage',
      component: MyPageView,
      children: [
        {
          path: '',
          name: 'mypage',
          component: MyPage,
        },
        {
          path: 'detail',
              name: 'mydetail',
              component: MyDetail,
        },
        {
          path: 'account',
          name: 'account',
          component: MyAccount,
        },
      ]

    }

    // {
    //   path: '/meeting',
    //   name: 'meeting',
    //   component: MeetingView,
    //   children:[{
    //     path: '/',
    //     name: 'meeting',
    //     component: MyMeeting,
    //   }]
    // }
  ]
})

export default router
