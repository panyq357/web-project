import { Link, useNavigate } from 'react-router-dom'

function Login() {
  const navigate = useNavigate()
  return (
    <>
      <Link to="/article">Go to Article page</Link>
      {" "}
      <button onClick={()=>navigate("/article")}>Go to Article page</button>
    </>
  )
}
