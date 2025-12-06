import { createBrowserRouter } from 'react-router-dom'
import Login from '../components/Login.jsx'
import Register from '../components/Register.jsx'
import App from '../App.jsx'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/login",
    element: <Login />
  },
  {
    path: "/register",
    element: <Register />
  },
  {
    path: "*",  // Fallback to the 404 page if no routes match.
    element: <h1>Page Not Found</h1>
  }
])

export default router
