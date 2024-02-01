import axiosInstance from '@/services/api'
import tokenService from '@/services/token.service'
import TokenService from '@/services/token.service'

const setup = () => {
  axiosInstance.interceptors.request.use(
    (config) => {
      const token = TokenService.getLocalAccessToken()
      if (token) {
        if (TokenService.expiredToken(token)) {
          // refesh token 요청
          const refreshToken = TokenService.getLocalRefreshToken()

          config.headers['Authorization-Refresh'] = refreshToken
        } else config.headers['Authorization'] = token
      }
      return config
    },
    (err) => {
      switch (err.status) {
        case 401:
          alert(err.errorMsg)
          break
        case 403:
          alert('User 권한이 없습니다')
          break
        case 409:
          alert('이미 가입한 회원입니다')
      }
      return Promise.reject(err)
    }
  )

  axiosInstance.interceptors.response.use(
    (res) => {
      const accessToken = res.headers.get('Authorization')
      const refreshToken = res.headers.get('Authorizatoin-Refresh')
      if (accessToken != null) tokenService.setLocalAccessToken(accessToken)
      if (refreshToken != null) tokenService.setLocalRefreshToken(refreshToken)

      return res
    },
    (err) => {
      return Promise.reject(err)
    }
  )
}

export default setup
