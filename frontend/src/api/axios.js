import axios from 'axios';
import useUserStore from '../store/useUserStore';

const api = axios.create({
  baseURL: 'http://localhost:8080'
});

api.interceptors.request.use(
  config => {
    const token = useUserStore.getState().token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

export default api;
