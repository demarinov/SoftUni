import {html, getNotification, data, getNavigation, getFooter, triggerError} from '../lib.js';

const registerTemplate = (onSubmit) => html`
${getNotification()}
${getNavigation()}
 
<section id="register">
            <form id="register-form" @submit = ${onSubmit}>
                <div class="container">
                    <h1>Register</h1>
                    <label for="username">Username</label>
                    <input id="username" type="text" placeholder="Enter Username" name="username">
                    <label for="email">Email</label>
                    <input id="email" type="text" placeholder="Enter Email" name="email">
                    <label for="password">Password</label>
                    <input id="password" type="password" placeholder="Enter Password" name="password">
                    <label for="repeatPass">Repeat Password</label>
                    <input id="repeatPass" type="password" placeholder="Repeat Password" name="repeatPass">
                    <div class="gender">
                        <input type="radio" name="gender" id="female" value="female">
                        <label for="female">Female</label>
                        <input type="radio" name="gender" id="male" value="male" checked>
                        <label for="male">Male</label>
                    </div>
                    <input type="submit" class="registerbtn button" value="Register">
                    <div class="container signin">
                        <p>Already have an account?<a href="/login">Sign in</a>.</p>
                    </div>
                </div>
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
            .querySelector('#register-form'));

        const email = formData.get('email');
        const username = formData.get('username');
        const password = formData.get('password');
        const repPassword = formData.get('repeatPass');
        const gender = formData.get('gender');

        if (email === '' || password === '' 
        || repPassword === '' || gender === ''
        || password !== repPassword || username === '') {
            console.error('Wrong username, email, password or gender!');
            // alert('Wrong username, email, password or gender!');
            triggerError('All fields are required', null);
            return;
        }

        let error = false;
        await data.regster(username, email, password, gender).catch(e => {error = true; msg = e.message});

        if (error) {
            triggerError(msg,null);
            return;
        }

        ctx.page.redirect('/memes');
        
    }
}
