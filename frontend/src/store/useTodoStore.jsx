import { create } from 'zustand';
import api from '../api/axios';

const useTodoStore = create((set) => ({

  todos: [],

  fetchTodos: () => {

    api.get("/todos")
    .then(response => {
      set({ todos: response.data.data })
    })
    .catch(error => {
      console.log(error)
    })
  },
  cleanTodos: () => {
    set({ todos: [] })
  }
}))

export default useTodoStore;
