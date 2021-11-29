// Get reference to the password input
const passwordEl = document.querySelector(".form-input--password");

// Get reference to the container for the eye image
const eyeContainerEl = document.querySelector(".password-eye-container");

// Get reference for the eye image
const eyeImgEl = eyeContainerEl.getElementsByTagName("img")[0];

// Show/hide the eye
passwordEl.addEventListener("keyup", (e) => {
  e.target.value.length
    ? eyeContainerEl.classList.remove("hidden")
    : eyeContainerEl.classList.add("hidden");
});

// Show/hide the password
eyeContainerEl.addEventListener("click", () => {
  if (eyeImgEl.classList.contains("eye-off")) {
    eyeImgEl.src = "/img/eye.png";
    eyeImgEl.classList.remove("eye-off");
    passwordEl.type = "text";
  } else {
    eyeImgEl.src = "/img/eye-off.png";
    eyeImgEl.classList.add("eye-off");
    passwordEl.type = "password";
  }
});

