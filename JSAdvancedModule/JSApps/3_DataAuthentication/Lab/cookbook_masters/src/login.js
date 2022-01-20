
const form = document.querySelector('form');

form.addEventListener('submit', loginUser);

function loginUser(ev) {
    ev.preventDefault();
    const formData = new FormData(ev.target);

    const email = formData.get('email');
    const password = formData.get('password');

    console.log(email);
    console.log(password);


    sendLoginRequest('http://localhost:3030/users/login'
        , { email, password });

}

async function sendLoginRequest(url, data) {


    const result = await fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    const response = await result.json();


    if (result.status === 200) {
        console.log('success');
        const authToken = response.accessToken;
        sessionStorage.setItem('authToken', authToken);
        let path = window.location.pathname;
        path = path.replace(/login.html/, 'index.html');
        console.log(path);
        window.location.pathname = path;

    } else {
        throw new Error(Object.values(response));
    }

}