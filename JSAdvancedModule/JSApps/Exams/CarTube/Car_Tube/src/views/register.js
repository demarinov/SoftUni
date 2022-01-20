import {html, getNotification, data, getNavigation, getFooter, triggerError} from '../lib.js';

const registerTemplate = (onSubmit) => html`
${getNavigation()}
 
<section id="register">
            <div class="container">
                <form id="register-form" @submit=${onSubmit}>
                    <h1>Register</h1>
                    <p>Please fill in this form to create an account.</p>
                    <hr>

                    <p>Username</p>
                    <input type="text" placeholder="Enter Username" name="username" required>

                    <p>Password</p>
                    <input type="password" placeholder="Enter Password" name="password" required>

                    <p>Repeat Password</p>
                    <input type="password" placeholder="Repeat Password" name="repeatPass" required>
                    <hr>

                    <input type="submit" class="registerbtn" value="Register">
                </form>
                <div class="signin">
                    <p>Already have an account?
                        <a href="/login">Sign in</a>.
                    </p>
                </div>
            </div>
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
            .querySelector('#register-form'));

        const username = formData.get('username');
        const password = formData.get('password');
        const repPassword = formData.get('repeatPass');

        if (password === '' 
        || repPassword === ''|| password !== repPassword 
        || username === '') {
            console.error('Wrong username or password!');
            alert('Wrong username or password');
            // triggerError('All fields are required', null);
            return;
        }

        let error = false;
        let msg = '';
        await data.regster(username, password).catch(e => {error = true; msg = e.message});

        if (error) {
            // triggerError(msg,null);
            alert(msg);
            return;
        }

        ctx.page.redirect('/listings');
        
    }
}
