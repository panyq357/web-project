import useTodoStore from '../store/useTodoStore'
import api from '../api/axios'

function ShowTodoList() {

  const { todos, fetchTodos } = useTodoStore()

  if (todos.length === 0) {
    fetchTodos()
  }

  function deleteTodo(id) {
    api.delete(`/todos/${id}`)
    .then(response => {
      fetchTodos()
    })
    .catch(error => {
      console.log(error)
    })
  }

  if (todos.length === 0) {
    return <>No todos yet.</>
  } else {
    return <table>
      {todos.map(todo => (<tr key={todo.id}>
        <td><button onClick={() => deleteTodo(todo.id)}>X</button></td><td>{todo.message}</td>
      </tr>))}
    </table>
  }
}

export default ShowTodoList
