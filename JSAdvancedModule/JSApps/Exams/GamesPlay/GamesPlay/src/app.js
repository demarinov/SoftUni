import {page, render, html, utils} from './lib.js';
import {gameSet} from './views/games.js';
import {homeSet} from './views/home.js';
import {loginSet} from './views/login.js';
import {registerSet} from './views/register.js';
import {gameDetailsSet} from './views/details.js';
import {createSet} from './views/create.js';
import {editSet} from './views/edit.js';
import {profileSet} from './views/profile.js';
import * as data from './api/data.js'; 


window.addEventListener('load', () => {
    console.log('Loading');
});

async function logout() {
    try {
        console.log('logging out');
        await data.logout();
        page.redirect('/home');
    } catch (err) {
        alert(err.message);
    }
}

const root = document.querySelector('#box');

async function decoratorFunction (ctx, next) {

    ctx.render = (template) => render(template, root); 
    ctx.renderInContainer = (template, container) => render(template, container); 
    ctx.updateNavigation = updateNavigation;
    next();
}

function updateNavigation(navType) {
    console.log('update nav');
    // sessionStorage.setItem('userToken', 'AAAAA');
    // sessionStorage.setItem('email','soni@abv.bg');
    if (sessionStorage.getItem('userToken') != null) {
        document.querySelector('#user').style.display = 'block';
        document.querySelector('#guest').style.display = 'none';

        // document.querySelector('#user a:nth-child(1)').
        // textContent = 'Welcome '+sessionStorage.getItem('username');

    } else {
        document.querySelector('#user').style.display = 'none';
        document.querySelector('#guest').style.display = 'block';

        // shift all memes
        // const memes = document.
        // querySelector('nav a[href="/memes"]');
        // memes.parentElement.removeChild(memes);

        // const meme = utils.e('a',{href:'/memes'},'All Memes');
        // document.
        // querySelector('nav').appendChild(meme);
    }

    const loginLink = document.querySelector('#guest a[href="/login"]');
    const registerLink = document.querySelector('#guest a[href="/register"]');
    const listingsLink = document.querySelector('nav a[href="/listings"]');
    const homeLink = document.querySelector('nav a[href="/home"]');
    const createLink = document.querySelector('#profile a[href="/create"]');
    const profileLink = document.querySelector('#profile a[href="/profile"]');
    const yearLink = document.querySelector('a[href="/year"]');

    // loginLink.classList.remove('active');
    // registerLink.classList.remove('active');
    // listingsLink.classList.remove('active');
    // homeLink.classList.remove('active');
    // createLink.classList.remove('active');
    // profileLink.classList.remove('active');
    // yearLink.classList.remove('active');

    // if (navType === 'login') {
    //     loginLink.classList.add('active');
    // } else if (navType === 'register') {
    //     registerLink.classList.add('active');
    // } else if (navType === 'home') {
    //     homeLink.classList.add('active');
    // } else if (navType === 'listing') {
    //     listingsLink.classList.add('active');
    // } else if (navType === 'create') {
    //     createLink.classList.add('active');
    // } else if (navType === 'profile') {
    //     profileLink.classList.add('active');
    // } else if (navType === 'year') {
    //     yearLink.classList.add('active');
    // } 


}

async function deleteSet(ctx) {
    console.log('deleteSet');

    const confirmed = confirm('Do you really want to delete this item?');

    if (!confirmed) {
        // page.redirect('/listings');
        return;
    }
    const id = ctx.params.id;
    await data.deleteGameById(id);

    page.redirect('/home');
}

// Routing ....
console.log('routing')
page(decoratorFunction);
page('/home', homeSet);
page('/games', gameSet);
page('/login', loginSet);
page('/register', registerSet);
page('/create', createSet);
page('/logout', logout);
page('/details/:id', gameDetailsSet);
page('/edit/:id', editSet);
// page('/year', yearSet);
// page('/delete/:id', deleteSet);
// page('/profile', profileSet);


page.redirect('/','/home');
page.redirect('/index.html','/home');

page.start();

