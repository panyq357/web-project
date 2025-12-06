import { create } from 'zustand'

function parseJwt(token) {
  try {
    const base64Url = token.split('.')[1]
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
    return JSON.parse(atob(base64))
  } catch(e) {
    return null
  }
}

const useUserStore = create((set) => {

  const token = localStorage.getItem("jwtToken")
  const payload = parseJwt(token)
  const username = payload?.username ?? null

  return {
    token: token,
    username: username,
    setUserStore: (token) => {
      localStorage.setItem("jwtToken", token)
      const payload = parseJwt(token)
      set({
        token: token,
        username: payload?.username ?? null
      })
    },
    logout: () => {
      localStorage.removeItem("jwtToken")
      set({ token: null, username: null })
    }
  }
})

export default useUserStore
