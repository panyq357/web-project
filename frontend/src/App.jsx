import useUserStore from './store/useUserStore'

import { Outlet, Navigate } from 'react-router-dom'

function App() {

  const { token } = useUserStore()

  if (token === null) {
    return <Navigate to="login" replace />
  }
  return <Outlet />
}

export default App
