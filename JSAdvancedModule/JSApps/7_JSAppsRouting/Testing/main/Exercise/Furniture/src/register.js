import {html, until, data} from '../src/lib.js';

const registerTemplate = (onSubmit) => html`
<div class="container">
        <div class="row space-top">
            <div class="col-md-12">
                <h1>Register New User</h1>
                <p>Please fill all fields.</p>
            </div>
        </div>
        <form @submit=${onSubmit}>
            <div class="row space-top">
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="form-control-label" for="email">Email</label>
                        <input class="form-control" id="email" type="text" name="email">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="password">Password</label>
                        <input class="form-control" id="password" type="password" name="password">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="rePass">Repeat</label>
                        <input class="form-control" id="rePass" type="password" name="rePass">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Register" />
                </div>
            </div>
        </form>
    </div>
`;


export async function registerSet(ctx) {
    console.log('registerSet');
    
    ctx.render(registerTemplate(onSubmit));
    ctx.updateNavigation('register');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit register form');
        
        const formData = new FormData(document.querySelector('.container form'));

        const email = formData.get('email');
        const password = formData.get('password');
        const repPassword = formData.get('rePass');

        if (email === '' || password === '' || repPassword === ''
        || password !== repPassword) {
            console.error('Wrong email or password!');
            alert('Wrong email or password!');
            return;
        }

        await data.regster(email, password);

        ctx.page.redirect('/catalog');
    }
}
