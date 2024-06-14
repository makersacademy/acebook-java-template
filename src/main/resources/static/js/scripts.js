document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded event fired");

    fetch("/auth/userLoggedIn")
        .then(response => response.json())
        .then(data => {
            const loginLink = document.getElementById("login-link");
            const signUpLink = document.getElementById("sign-up-link");

            if (data.loggedIn) {
                loginLink.textContent = "log out";
                loginLink.addEventListener("click", function(event) {
                    event.preventDefault();

                    fetch("/logout", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                            'X-CSRF-Token': document.querySelector('meta[name="csrf-token"]').getAttribute('content')
                        }
                    })
                    .then(response => {
                        if (response.ok) {
                            window.location.href = "/login?logout";
                        } else {
                            console.error('Logout failed');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                });
                signUpLink.style.display = "none";
            } else {
                loginLink.textContent = "log in";
                loginLink.href = "/login";
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
