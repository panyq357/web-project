import useUserStore from './store/useUserStore'
import { Navigate } from 'react-router-dom'
import Logout from './components/Logout'
import NewTodo from './components/NewTodo'
import ShowTodoList from './components/ShowTodoList'

function App() {

  const { token } = useUserStore()

  if (token === null) {
    return <Navigate to="login" replace />
  }
  return(<>
    <div>
      <Logout />
    </div>
    <div>
      <NewTodo />
    </div>
    <div>
      <ShowTodoList />
    </div>
  </>)
}

export default App
