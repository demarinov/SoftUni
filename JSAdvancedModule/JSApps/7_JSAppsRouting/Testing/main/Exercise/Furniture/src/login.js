import {html, until, data} from '../src/lib.js';

const loginTemplate = (onSubmit) => html`
<div class="container">
        <div class="row space-top">
            <div class="col-md-12">
                <h1>Login User</h1>
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
                    <input type="submit" class="btn btn-primary" value="Login" />
                </div>
            </div>
        </form>
    </div>
`;


export async function loginSet(ctx) {
    console.log('loginSet');
    
    ctx.render(loginTemplate(onSubmit));
    ctx.updateNavigation('login');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit login form');
        
        const formData = new FormData(document.querySelector('.container form'));

        const email = formData.get('email');
        const password = formData.get('password');

        if (email === '' || password === '') {
            console.error('Empty fields not allowed');
            alert('Empty fields not allowed');
            return;
        }

        await data.login(email, password).catch(e => alert(e.message));

        ctx.page.redirect('/catalog');
    }
}

