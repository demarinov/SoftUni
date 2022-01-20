import {html, getNotification, data, getNavigation, getFooter, triggerError} from '../lib.js';

const registerTemplate = (onSubmit) => html`
${getNavigation()}
 
<section id="register-page" class="content auth">
            <form id="register" @submit=${onSubmit}>
                <div class="container">
                    <div class="brand-logo"></div>
                    <h1>Register</h1>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="maria@email.com">

                    <label for="pass">Password:</label>
                    <input type="password" name="password" id="register-password">

                    <label for="con-pass">Confirm Password:</label>
                    <input type="password" name="confirm-password" id="confirm-password">

                    <input class="btn submit" type="submit" value="Register">

                    <p class="field">
                        <span>If you already have profile click <a href="/login">here</a></span>
                    </p>
                </div>
            </form>
        </section>   
`;


export async function registerSet(ctx) {
    console.log('registerSet');
    
    ctx.render(registerTemplate(onSubmit));
    ctx.updateNavigation('register');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit register form');
        
        const formData = new FormData(document
            .querySelector('#register'));

        const email = formData.get('email');
        const password = formData.get('password');
        const repPassword = formData.get('confirm-password');

        if (password === '' 
        || repPassword === ''|| password !== repPassword 
        || email === '') {
            console.error('Wrong email or password!');
            alert('Wrong email or password');
            // triggerError('All fields are required', null);
            return;
        }

        let error = false;
        let msg = '';
        await data.regster(email, password).catch(e => {error = true; msg = e.message});

        if (error) {
            // triggerError(msg,null);
            alert(msg);
            return;
        }

        ctx.page.redirect('/home');
        
    }
}
