import useUserStore from '../store/useUserStore'
import useTodoStore from '../store/useTodoStore'

function Logout() {

  const { username, logout } = useUserStore()

  const cleanTodos = useTodoStore(state=>state.cleanTodos)

  function cleanStates() {
    logout()
    cleanTodos()
  }

  return (
    <>
      <span>User {username} logged in. </span>
      <button onClick={cleanStates}>Logout</button>
    </>
  )
}

export default Logout
