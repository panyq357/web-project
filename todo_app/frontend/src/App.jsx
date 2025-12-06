import useUserStore from './store/useUserStore'
import { Navigate } from 'react-router-dom'
import Logout from './components/Logout'
import NewTodo from './components/NewTodo'
import TodoList from './components/TodoList'

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
      <TodoList />
    </div>
  </>)
}

export default App
