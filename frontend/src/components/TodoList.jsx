import { useState, useEffect, useCallback } from 'react'
import useTodoStore from '../store/useTodoStore'
import api from '../api/axios'


function DeleteButton({ id }) {

  const fetchTodos = useTodoStore(state=>state.fetchTodos)

  const handleDelete = useCallback(() => {
    api.delete(`/todos/${id}`)
    .then(fetchTodos)
    .catch(error => console.log(error))
  }, [id, fetchTodos])

  return <button onClick={handleDelete}>X</button>
}


function EditableMessage({ id, message }) {

  const fetchTodos = useTodoStore(state=>state.fetchTodos)

  const [editing, setEditing] = useState(false)
  const [draft, setDraft] = useState(message)

  const handleEdit = useCallback(() => {

    api.put(`/todos/${id}`, { message: draft })
    .then(fetchTodos)
    .catch(error => console.log(error))

    setEditing(false)
  }, [draft, fetchTodos])

  return <>{editing ? (
    <input
      type="text"
      value = {draft}
      onChange = {(e) => setDraft(e.target.value)}
      onBlur = {handleEdit}
    />
  ) : (
    <span onClick={() => setEditing(true)}>{draft}</span>
  )}</>
}


function TodoItem({ todo }) {

  return <tr>
    <td><DeleteButton id={todo.id} /></td>
    <td><EditableMessage id={todo.id} message={todo.message} /></td>
  </tr>
}


function TodoList() {

  const { todos, fetchTodos } = useTodoStore()

  // Run only once when loading component.
  useEffect(fetchTodos, [])

  if (todos.length === 0) {
    return <>No todos yet.</>
  }

  const items = todos.map(todo => <TodoItem key={todo.id} todo={todo} />)

  return <table>
    <tbody>
    {items}
    </tbody>
  </table>
}

export default TodoList
