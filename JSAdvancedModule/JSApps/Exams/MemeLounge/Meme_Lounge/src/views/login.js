import {html, getNotification, data, 
    getFooter, getNavigation, triggerError} from '../lib.js';


const loginTemplate = (onSubmit) => html`
${getNotification()}
${getNavigation()}
<section id="login">
<form id="login-form" @submit=${onSubmit}>
                <div class="container">
                    <h1>Login</h1>
                    <label for="email">Email</label>
                    <input id="email" placeholder="Enter Email" name="email" type="text">
                    <label for="password">Password</label>
                    <input id="password" type="password" placeholder="Enter Password" name="password">
                    <input type="submit" class="registerbtn button" value="Login">
                    <div class="container signin">
                        <p>Dont have an account?<a href="/register">Sign up</a>.</p>
                    </div>
                </div>
</form>
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

        const email = formData.get('email');
        const password = formData.get('password');

        if (email === '' || password === '') {
            console.error('Empty fields not allowed');
            // alert('Empty fields not allowed');
            
            triggerError('Empty fields not allowed', null);  
            return;
        }

        let error = false;
        let msg = '';
        await data.login(email, password).catch(e => {error = true; msg = e.message});

        if (error) {
            triggerError(msg, null);
            return;
        }
        
        ctx.page.redirect('/memes');
        
    }
}

