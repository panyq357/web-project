import { useState } from 'react'
import axios from 'axios'

function Login({ onTokenChange }) {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const [status, setStatus] = useState({code: 200, message: ""})


  function handleLogin(e) {
    e.preventDefault()
    axios.post("http://localhost:8080/login", {
      username: username,
      password: password
    })
    .then(response => {
      setStatus({
        code: response.data.code,
        message: response.data.message
      })
      if (response.data.code === 200) {
        localStorage.setItem("jwtToken", response.data.data)
        onTokenChange(response.data.data)
      }
    })
    .catch(error => {
      setStatus({
        code: 400,
        message: "Login failed!"
      })
      console.error(error)
    })
  }


  return (
    <>
      <form>
        <fieldset>
          <legend>
            Login
          </legend>
          <div>
            <label htmlFor="username">Username: </label>
            <input type="text" id="username" value={username} onInput={(e)=>setUsername(e.target.value)}  required />
          </div>
          <div>
            <label htmlFor="password">Password: </label>
            <input type="password" id="password" value={password} onInput={(e)=>setPassword(e.target.value)} required />
          </div>
          <div>
          <button type="submit" onClick={handleLogin}>Submit</button>
          <span style={status.code == 200 ? {color: "green"} : {color: "red"}}> {status.message}</span>
          </div>
        </fieldset>
      </form>
    </>
  )
}

export default Login
