document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded event fired");

    // Make an AJAX request to check user's login status
    fetch("/auth/userLoggedIn")
        .then(response => response.json())
        .then(data => {
        const loginLink = document.getElementById("login-link");
        const signUpLink = document.getElementById("sign-up-link");

        if (data.loggedIn) {
            // User is logged in
            loginLink.textContent = "log out";
            loginLink.href = "/logout";
            signUpLink.style.display = "none"; // Hide the sign-up link
        } else {
            // User is not logged in
            loginLink.textContent = "log in";
            loginLink.href = "/login";
        }
    })
        .catch(error => {
        console.error('Error:', error);
    });
});
