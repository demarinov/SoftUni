import {html, data, getNavigation, getFooter} from '../lib.js';

const registerTemplate = (onSubmit) => html`
${getNavigation()}
<section id="registerPage">
<form @submit=${onSubmit}>
    <fieldset>
        <legend>Register</legend>

        <label for="email" class="vhide">Email</label>
        <input id="email" class="email" name="email" type="text" placeholder="Email">

        <label for="password" class="vhide">Password</label>
        <input id="password" class="password" name="password" type="password" placeholder="Password">

        <label for="conf-pass" class="vhide">Confirm Password:</label>
        <input id="conf-pass" class="conf-pass" name="conf-pass" type="password" placeholder="Confirm Password">

        <button type="submit" class="register">Register</button>

        <p class="field">
            <span>If you already have profile click <a href="/login">here</a></span>
        </p>
    </fieldset>
</form>
</section>

${getFooter()}
`;


export async function registerSet(ctx) {
    console.log('registerSet');
    
    ctx.render(registerTemplate(onSubmit));
    ctx.updateNavigation('register');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit register form');
        
        const formData = new FormData(document
            .querySelector('#registerPage form'));

        const email = formData.get('email');
        const password = formData.get('password');
        const repPassword = formData.get('conf-pass');

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
