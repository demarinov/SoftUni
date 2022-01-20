
import {getNavigation, getFooter, html, render} from '../lib.js';

const welcomeTemplate = () => {

    return html `
    ${getNavigation()}
    <section id="welcome">
    <!-- Welcome Page ( Only for guest users ) -->
        <div id="welcome-container">
            <h1>Welcome To Meme Lounge</h1>
            <img src="/images/welcome-meme.jpg" alt="meme">
            <h2>Login to see our memes right away!</h2>
            <div id="button-div">
                <a href="/login" class="button">Login</a>
                <a href="/register" class="button">Register</a>
            </div>
        </div>
    </section>
    ${getFooter()}
    `;
}


export async function homeSet(ctx) {
    console.log('homeSet');

    ctx.render(welcomeTemplate());

    ctx.updateNavigation('home');
    
    if (sessionStorage.getItem('userToken') != null) {
        //
        // page.redirect('/memes');
    }
}