class TokenService {

  getLocalRefreshToken = () => {
    return sessionStorage.getItem('user') ? this.refreshToken : null
  }

  getLocalAccessToken = () => {
    return sessionStorage.getItem('user') ? this.accessToken : null
  }

  setLocalRefreshToken = (token) => {
    this.$cookie.set('refresh-token', token);
  }

  setLocalAccessToken = (token) => {
    this.$cookie.set('access-token', token);
  }

  updateLocalAccessToken = (token) => {
    this.$cookie.set('access-token', token);
  }

  getUser = () => {
    return JSON.parse(sessionStorage.getItem('user'))
  }

  setUser = (user) => {
    console.log(JSON.stringify(user))
    this.$cookie.set('user', JSON.stringify(user))
  }

  removeUser = () => {
    this.$cookie.remove('user');
    this.$cookie.remove('access-token');
    this.$cookie.remove('refresh-token');
  }

  parseJwt = (token) => {
    const base64Url = token.split('.')[1]
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(function (c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
        })
        .join('')
    )
    return JSON.parse(jsonPayload)
  }

  expiredToken = () => {
    return this.parseJwt(this.accessToken).exp * 1000 <= Date.now()
  }
}

export default new TokenService()
