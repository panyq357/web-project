import useUserStore from './store/useUserStore'
import Login from './components/Login'
import Logout from './components/Logout'

function App() {

  const { token } = useUserStore()

  if (token === null) {
    return <Login />
  } else {
    return <Logout />
  }
}

export default App
