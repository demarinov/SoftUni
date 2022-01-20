import {html, getNotification, data, 
    getFooter, getNavigation, triggerError} from '../lib.js';


const loginTemplate = (onSubmit) => html`
${getNavigation()}
<section id="login-page" class="auth">
<form id="login" @submit=${onSubmit}>

    <div class="container">
        <div class="brand-logo"></div>
        <h1>Login</h1>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Sokka@gmail.com">

        <label for="login-pass">Password:</label>
        <input type="password" id="login-password" name="password">
        <input type="submit" class="btn submit" value="Login">
        <p class="field">
            <span>If you don't have profile click <a href="/register">here</a></span>
        </p>
    </div>
</form>
</section>
`;


export async function loginSet(ctx) {
    console.log('loginSet');

    ctx.render(loginTemplate(onSubmit));
    ctx.updateNavigation('login');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit login form');
        
        const formData = new FormData(document.querySelector('#login'));

        const email = formData.get('email');
        const password = formData.get('password');

        if (email === '' || password === '') {
            console.error('Empty fields not allowed');
            alert('Empty fields not allowed');
            
            // triggerError('Empty fields not allowed', null);  
            return;
        }

        let error = false;
        let msg = '';
        await data.login(email, password).catch(e => {error = true; msg = e.message});

        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }
        
        ctx.page.redirect('/home');
        
    }
}

