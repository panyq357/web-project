import { useState } from 'react'
import api from '../api/axios';

import useTodoStore from '../store/useTodoStore'

function NewToDo() {

  const [todo, setTodo] = useState("");
  const [status, setStatus] = useState("");

  const fetchTodos = useTodoStore(state => state.fetchTodos)

  function handleSubmit(e) {
    e.preventDefault()
    api.post("/todos", { message: todo })
    .then(response => {
      setStatus({
        code: response.data.code,
        message: response.data.message
      })
      fetchTodos()
    })
    .catch(error => {
      setStatus({
        code: 400,
        message: "Failed to create new Todo."
      })
      console.error(error)
    })
  }

  return(
    <>
      <form>
        <label htmlFor="todo">New TODO: </label>
        <input id="todo" type="text" value={todo} onChange={(e)=>setTodo(e.target.value)} /> {" "}
        <button type="submit" onClick={handleSubmit}>Submit</button>
        <span style={status.code == 200 ? {color: "green"} : {color: "red"}}> {status.message}</span>
      </form>
    </>
  )
}

export default NewToDo
