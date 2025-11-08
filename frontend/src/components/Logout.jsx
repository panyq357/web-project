function Logout({ token, onTokenChange }) {
  
  function getUsername() {
    const payload = JSON.parse(atob(token.split(".")[1]))
    return payload.username
  }

  function handleLogout () {
    localStorage.removeItem("jwtToken")
    onTokenChange(null)
  }
  return (
    <>
      <span>User {getUsername()} logged in. </span>
      <button onClick={handleLogout}>Logout</button>
    </>
  )
}

export default Logout
