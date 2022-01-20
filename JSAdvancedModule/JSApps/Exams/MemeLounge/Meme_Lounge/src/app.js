import {page, render, html, utils} from './lib.js';
import {memeSet} from './views/memes.js';
import {homeSet} from './views/home.js';
import {loginSet} from './views/login.js';
import {registerSet} from './views/register.js';
import {memeDetailsSet} from './views/details.js';
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

const root = document.querySelector('#container');

async function decoratorFunction (ctx, next) {

    ctx.render = (template) => render(template, root); 
    ctx.updateNavigation = updateNavigation;
    next();
}

function updateNavigation(navType) {
    console.log('update nav');

    if (sessionStorage.getItem('userToken') != null) {
        document.querySelector('.user').style.display = 'block';
        document.querySelector('.guest').style.display = 'none';

        document.querySelector('.profile span').
        textContent = 'Welcome, '+sessionStorage.getItem('email');
    } else {
        document.querySelector('.user').style.display = 'none';
        document.querySelector('.guest').style.display = 'block';

        // shift all memes
        const memes = document.
        querySelector('nav a[href="/memes"]');
        memes.parentElement.removeChild(memes);

        const meme = utils.e('a',{href:'/memes'},'All Memes');
        document.
        querySelector('nav').appendChild(meme);
    }

    const loginLink = document.querySelector('.profile a[href="/login"]');
    const registerLink = document.querySelector('.profile a[href="/register"]');
    const memeLink = document.querySelector('nav a[href="/memes"]');
    const homeLink = document.querySelector('.guest a[href="/home"]');
    const createLink = document.querySelector('.user a[href="/create"]');
    const profileLink = document.querySelector('.user a[href="/profile"]');

    loginLink.classList.remove('active');
    registerLink.classList.remove('active');
    memeLink.classList.remove('active');
    homeLink.classList.remove('active');
    createLink.classList.remove('active');
    profileLink.classList.remove('active');

    if (navType === 'login') {
        loginLink.classList.add('active');
    } else if (navType === 'register') {
        registerLink.classList.add('active');
    } else if (navType === 'meme') {
        memeLink.classList.add('active');
    } else if (navType === 'home') {
        homeLink.classList.add('active');
    } else if (navType === 'create') {
        createLink.classList.add('active');
    } else if (navType === 'profile') {
        profileLink.classList.add('active');
    } 


}

async function deleteSet(ctx) {
    console.log('deleteSet');

    const confirmed = confirm('Do you really want to delete this item?');

    if (!confirmed) {
        page.redirect('/memes');
        return;
    }
    const id = ctx.params.id;
    await data.deleteMemeById(id);

    page.redirect('/memes');
}

// Routing ....
console.log('routing')
page(decoratorFunction);
page('/home', homeSet);
page('/memes', memeSet);
page('/login', loginSet);
page('/register', registerSet);
page('/create', createSet);
page('/logout', logout);
page('/details/:id', memeDetailsSet);
page('/edit/:id', editSet);
page('/delete/:id', deleteSet);
page('/profile', profileSet);


page.redirect('/','/home');
page.redirect('/index.html','/home');

page.start();

