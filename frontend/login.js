const backendUrl = "http://localhost:8080/login"

const form = document.querySelector("#login-form");

const formStatus = document.getElementById("form-status");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  // Send to back-end
  axios.post(backendUrl, {
    username: username,
    password: password,
  })
  .then(response => {
    if (response.data.code == 400) {
      formStatus.style.color = "red";
    } else if (response.data.code == 200) {
      formStatus.style.color = "green";
      localStorage.setItem("jwtToken", response.data.data);
    }
    formStatus.innerHTML = response.data.message;

  })
  .catch(error => {
    formStatus.innerHTML = "Register failed!";
    formStatus.style.color = "red";
    console.error(error);
  })
});
