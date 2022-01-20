
function applyLogin(runCatalog) {

    //login form functionality
    const form = document.querySelector('#login form');

    form.addEventListener('submit', async function (e) {
        e.preventDefault();
        await loginUser(e);

        runCatalog();

        const catalogBtn = document.querySelector('#catalogBtn');
        catalogBtn.className = "active";

        const registerBtn = document.querySelector('#registerBtn');
        registerBtn.className = '';

        const loginBtn = document.querySelector('#loginBtn');
        loginBtn.className = '';

        const articleLogin = document.querySelector('#login');
        articleLogin.style.display = 'none';

    });

    async function loginUser(ev) {

        const formData = new FormData(ev.target);

        const email = formData.get('email');
        const password = formData.get('password');


        await sendLoginRequest('http://localhost:3030/users/login'
            , { email, password });

        // clear form data
        ev.target.reset();
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
            const authToken = response.accessToken;
            sessionStorage.setItem('authToken', authToken);
            sessionStorage.setItem('userId', response._id);
            // let path = window.location.pathname;
            // path = path.replace(/index.html/, 'index.html');
            // console.log(path);
            // window.location.pathname = path;

        } else {
            throw new Error(Object.values(response));
        }

    }

    // show login page
    const loginArticle = document.querySelector('main #login');

    loginArticle.style.display = "block";

}

export { applyLogin };


