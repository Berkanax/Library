document.addEventListener('DOMContentLoaded', function() {
    const registerForm = document.querySelector('#register-form');
    const loginForm = document.querySelector('#login-form');

    if (registerForm) {
        registerForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(registerForm);
            fetch('/register', {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    window.location.href = '/welcome';
                } else {
                    alert('Rejestracja nie powiodła się. Proszę spróbować ponownie.');
                }
            });
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(loginForm);
            fetch('/login', {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    window.location.href = '/welcome';
                } else {
                    alert('Logowanie nie powiodło się. Proszę spróbować ponownie.');
                }
            });
        });
    }
});
