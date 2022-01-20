
window.addEventListener('load', function(e) {
    const formBtn = document.querySelector('form button');

    if (sessionStorage.getItem('authToken') === null) {
        document.getElementById('user').style.display = 'none';
    }

    formBtn.addEventListener('click', function (e) {
        e.preventDefault();
        loginAttempt();
    });

});



async function loginAttempt() {
    const email = document.querySelector('input[name=email]');
    const password = document.querySelector('input[name=password]');

    const data = {
        "email": email.value,
        "password":password.value
    }

    await sendPostLoginRequest(data);

    // clear fields
    email.value = '';
    password.value = '';
}

async function sendPostLoginRequest(data) {

    const response = await fetch('http://localhost:3030/users/login', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.status == 200) {
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        sessionStorage.setItem('user', 
        JSON.stringify({'email':data.email, 'id':data['_id']}));
        let path = window.location.pathname;
        path = path.replace(/login.html/, 'index.html');
        window.location.pathname = path;
    } else {
        console.error('Error: Unable to login!');
    }

}