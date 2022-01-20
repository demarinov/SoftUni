import {html, getNotification, data, 
    getFooter, getNavigation, triggerError} from '../lib.js';


const loginTemplate = (onSubmit) => html`
${getNavigation()}
<section id="login">
            <div class="container">
                <form id="login-form" @submit=${onSubmit}>
                    <h1>Login</h1>
                    <p>Please enter your credentials.</p>
                    <hr>

                    <p>Username</p>
                    <input placeholder="Enter Username" name="username" type="text">

                    <p>Password</p>
                    <input type="password" placeholder="Enter Password" name="password">
                    <input type="submit" class="registerbtn" value="Login">
                </form>
                <div class="signin">
                    <p>Dont have an account?
                        <a href="/register">Sign up</a>.
                    </p>
                </div>
            </div>
        </section>
${getFooter()}
`;


export async function loginSet(ctx) {
    console.log('loginSet');

    ctx.render(loginTemplate(onSubmit));
    ctx.updateNavigation('login');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit login form');
        
        const formData = new FormData(document.querySelector('#login-form'));

        const username = formData.get('username');
        const password = formData.get('password');

        if (username === '' || password === '') {
            console.error('Empty fields not allowed');
            alert('Empty fields not allowed');
            
            // triggerError('Empty fields not allowed', null);  
            return;
        }

        let error = false;
        let msg = '';
        await data.login(username, password).catch(e => {error = true; msg = e.message});

        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }
        
        ctx.page.redirect('/listings');
        
    }
}

