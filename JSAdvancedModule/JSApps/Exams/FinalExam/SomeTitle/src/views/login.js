import {html, data, 
    getFooter, getNavigation} from '../lib.js';


const loginTemplate = (onSubmit) => html`
${getNavigation()}
<section id="loginPage">
            <form @submit=${onSubmit}>
                <fieldset>
                    <legend>Login</legend>

                    <label for="email" class="vhide">Email</label>
                    <input id="email" class="email" name="email" type="text" placeholder="Email">

                    <label for="password" class="vhide">Password</label>
                    <input id="password" class="password" name="password" type="password" placeholder="Password">

                    <button type="submit" class="login">Login</button>

                    <p class="field">
                        <span>If you don't have profile click <a href="/register">here</a></span>
                    </p>
                </fieldset>
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
       
        const formData = new FormData(document.querySelector('#loginPage form'));

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

