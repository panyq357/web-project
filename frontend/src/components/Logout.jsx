import useUserStore from '../store/useUserStore'

function Logout() {

  const { username, logout } = useUserStore()
  
  return (
    <>
      <span>User {username} logged in. </span>
      <button onClick={logout}>Logout</button>
    </>
  )
}

export default Logout
