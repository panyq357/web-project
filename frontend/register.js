const backendUrl = "http://localhost:8080/users"

const form = document.getElementById("register-form");
const formStatus = document.getElementById("form-status");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const username = document.getElementById("username").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const passwordAgain = document.getElementById("password-again").value;

  if (password === passwordAgain) {
    // Send to back-end
    axios.post(backendUrl, {
      username: username,
      password: password,
      email: email
    })
    .then(response => {
      if (response.data.code == 400) {
        formStatus.style.color = "red";
      } else if (response.data.code == 200) {
        formStatus.style.color = "green";
      }
      formStatus.innerHTML = response.data.message;

    })
    .catch(error => {
      formStatus.innerHTML = "Register failed!";
      formStatus.style.color = "red";
      console.error(error);
    })

  } else {
    formStatus.innerHTML = "The two password must match!";
    formStatus.style.color = "red";
  }
});
