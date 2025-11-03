const backendUrl = "http://localhost:8080/users"

const form = document.getElementById("unregister-form");
const formStatus = document.getElementById("form-status");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const passwordAgain = document.getElementById("password-again").value;

  if (password === passwordAgain) {
    // Send to back-end
    axios.delete(backendUrl, {
      data: {
        username: username,
        password: password
      }
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
      formStatus.innerHTML = "Unregister failed!";
      formStatus.style.color = "red";
      console.error(error);
    })

  } else {
    formStatus.innerHTML = "The two password must match!";
    formStatus.style.color = "red";
  }
});
