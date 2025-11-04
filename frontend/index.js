const backendUrl = "http://localhost:8080/users"
const userList = document.querySelector("#user-list");

const login = document.querySelector("#login");
const jwtToken = localStorage.getItem("jwtToken");
if (jwtToken === null) {
  login.innerHTML = "<a href='login.html'>Login</a>"
} else {
  // Split token.
  const payloadBase64 = jwtToken.split('.')[1];
  // Base64URL -> Base64.
  const payloadBase64Standard = payloadBase64.replace(/-/g, '+').replace(/_/g, '/');
  // parse JSON.
  const payload = JSON.parse(atob(payloadBase64Standard));

  login.innerHTML = `User ${payload.username} logged in.`

  const logout = document.createElement("a");

  // Insert logout after login.
  login.after(logout);

  logout.innerHTML = "Logout";
  logout.href = "#";

  logout.addEventListener("click", (e) => {
     e.preventDefault();
     localStorage.removeItem("jwtToken");
     window.location.reload();
  });
}

axios.get(backendUrl)
.then(response => {
  userList.innerHTML = response.data.data.map(user => `<li>${user}</li>`).join("");
});
