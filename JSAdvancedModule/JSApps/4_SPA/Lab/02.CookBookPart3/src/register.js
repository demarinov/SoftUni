function applyRegister(runCatalog) {

    registerUser(runCatalog);

    function registerUser(runCatalog) {
        console.log('Register user')

        const form = document.querySelector('#register form');

        form.addEventListener('submit', async function (ev) {

            ev.preventDefault();
            const formData = new FormData(ev.target);

            const email = formData.get('email');
            const password = formData.get('password');
            const repeatPass = formData.get('rePass');

            if (password !== repeatPass) {
                return console.error('Passwords do not match!');
            }

            let error = false;
            await sendRegistrationRequest('http://localhost:3030/users/register'
                , { email, password, repeatPass }).catch(e => {
                    console.error(e);
                    error = true;                    
                });

            if (error) {
                ev.target.reset();
                return;
            }
            runCatalog();

            const catalogBtn = document.querySelector('#catalogBtn');
            catalogBtn.className = "active";

            const registerBtn = document.querySelector('#registerBtn');
            registerBtn.className = '';

            const loginBtn = document.querySelector('#loginBtn');
            loginBtn.className = '';

            const articleCreate = document.querySelector('#register');
            articleCreate.style.display = 'none';

        });

        // show register page view
        const registerArticle = document.querySelector('main #register');

        registerArticle.style.display = "block";
    }

    async function sendRegistrationRequest(url, data) {

        console.log('sendRegistrationRequest');
        const result = await fetch(url, {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (result.status === 200) {
            const response = await result.json();
            const authToken = response.accessToken;
            sessionStorage.setItem('authToken', authToken);
            sessionStorage.setItem('userId', response._id);

        } else {
            console.error(result.status);
            throw new Error(await result.text());
        }

    }


}

export {applyRegister};
