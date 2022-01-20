
window.addEventListener('load', registerUser);

function registerUser() {

    const form = document.querySelector('form');

    form.addEventListener('submit', (ev => {
        ev.preventDefault();
        const formData = new FormData(ev.target);

        const email = formData.get('email');
        const password = formData.get('password');
        const repeatPass = formData.get('rePass');

        console.log(email);
        console.log(password);
        console.log(repeatPass);

        if (password !== repeatPass) {
            return console.error('Passwords do not match!');
        }

        sendRegistrationRequest('http://localhost:3030/users/register'
            , { email, password, repeatPass });


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

    const response = result.json();

    response
        .then(data => {

            if (result.status === 200) {
                console.log('success');
                const authToken = data.accessToken;
                sessionStorage.setItem('authToken', authToken);
                let path = window.location.pathname;
                path = path.replace(/register.html/,'index.html');
                console.log(path);
                window.location.pathname = path;
                
            } else {
                throw new Error(Object.values(data));
            }
        })
        .catch(e => console.log('custom: ' + e));

}