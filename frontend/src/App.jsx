import { useState } from 'react'

import Login from './components/Login'
import Logout from './components/Logout'

function App() {
  const [token, setToken] = useState(()=>{return localStorage.getItem("jwtToken")})
  if (token === null) {
    return <Login onTokenChange={setToken} />
  } else {
    return <Logout token={token} onTokenChange={setToken} />
  }
}

export default App
