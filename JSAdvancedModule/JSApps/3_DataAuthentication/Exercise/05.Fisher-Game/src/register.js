
window.addEventListener('load', registerUser);

function registerUser() {

    if (sessionStorage.getItem('authToken') === null) {
        document.getElementById('user').style.display = 'none';
    }
    const form = document.querySelector('form');

    form.addEventListener('submit', (ev => {
        ev.preventDefault();

        const formData = new FormData(ev.target);

        const email = formData.get('email');
        const password = formData.get('password');

        sendRegistrationRequest('http://localhost:3030/users/register'
            , { email, password});


    }));
}

async function sendRegistrationRequest(url, data) {


    const result = await fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    
    if (result.status === 200) {
        const response = await result.json();
        const data = response;
        const authToken = data.accessToken;
        sessionStorage.setItem('authToken', authToken);
        sessionStorage.setItem('user', 
        JSON.stringify({'email':data.email, 'id':data['_id']}));
        let path = window.location.pathname;
        path = path.replace(/register.html/, 'index.html');
        window.location.pathname = path;

    } else {
        console.error('Error: Unable to register!');
    }


}