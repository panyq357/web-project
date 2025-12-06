import { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

function Register() {

  const navigate = useNavigate()

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [passwordAgain, setPasswordAgain] = useState("")
  const [email, setEmail] = useState("")

  const [status, setStatus] = useState({code: 200, message: ""})

  function handleRegister(e) {

    e.preventDefault()

    if (password !== passwordAgain) {
      setStatus({
        code: 400,
        message: "Two password must match!"
      })
      return
    }

    axios.post("http://localhost:8080/register", {
      username: username,
      password: password,
      email: email
    })
    .then(response => {
      setStatus({
        code: response.data.code,
        message: response.data.message
      })

    })
    .catch(error => {
      setStatus({
        code: 400,
        message: "Register failed!"
      })
      console.error(error)
    })
  }


  return (
    <>
      <form>
        <fieldset>
          <legend>
            Register
          </legend>
          <div>
            <label htmlFor="username">Username: </label>
            <input type="text" id="username" value={username} onInput={(e)=>setUsername(e.target.value)}  required />
          </div>
          <div>
            <label htmlFor="email">Email: </label>
            <input type="text" id="email" value={email} onInput={(e)=>setEmail(e.target.value)}  required />
          </div>
          <div>
            <label htmlFor="password">Password: </label>
            <input type="password" id="password" value={password} onInput={(e)=>setPassword(e.target.value)} required />
          </div>
          <div>
            <label htmlFor="password-again">Password Again: </label>
            <input type="password" id="password-again" value={passwordAgain} onInput={(e)=>setPasswordAgain(e.target.value)} required />
          </div>
          <div>
          <button type="submit" onClick={handleRegister}>Submit</button> {" "}
          <button type="button"  onClick={()=>navigate("/login")}>Login</button>
          <span style={status.code == 200 ? {color: "green"} : {color: "red"}}> {status.message}</span>
          </div>
        </fieldset>
      </form>
    </>
  )
}

export default Register
