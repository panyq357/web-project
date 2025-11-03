const backendUrl = "http://localhost:8080/users"
const userList = document.querySelector("#user-list");

axios.get(backendUrl)
.then(response => {
  userList.innerHTML = response.data.data.map(user => `<li>${user}</li>`).join("");
});
