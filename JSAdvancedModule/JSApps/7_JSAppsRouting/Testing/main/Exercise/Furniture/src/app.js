import {page, render} from '../src/lib.js';
import {catalogSet, catalogPublicationsSet} from './catalog.js';
import {loginSet} from './login.js';
import {registerSet} from './register.js';
import {catalogDetailsSet} from './details.js';
import {createSet} from './create.js';
import {editSet} from './edit.js';
import * as data from '../src/api/data.js'; 


window.addEventListener('load', () => {
    console.log('Loading');
    document.getElementById('logoutBtn').addEventListener('click', logout);

    // updateNavigation('catalog');
    async function logout() {
        try {
            await data.logout();
            page.redirect('/catalog');
        } catch (err) {
            alert(err.message);
        }
    }
});

const root = document.querySelector('body');

async function decoratorFunction (ctx, next) {

    ctx.render = (template) => render(template, root); 
    ctx.updateNavigation = updateNavigation;
    ctx.validationRules = validationRules;
    next();
}

const validationRules = {
    "make": (value) => {
        return value.length < 4 ? false : true;
    },
    "model": (value) => {
        return value.length < 4 ? false : true;
    },
    "year":(value) => {
        return value < 1950 || value >2050 ? false : true;
    },
    "description": (value) => {
        return value.length <= 10 ? false : true;
    },
    "price":(value) => {
        return value === '' || value < 0 ? false : true;
    },
    "img":(value) => {
        return value === '' ? false : true;
    },
};

function updateNavigation(navType) {

    if (sessionStorage.getItem('userToken') != null) {
        document.getElementById('user').style.display = 'inline-block';
        document.getElementById('guest').style.display = 'none';
    } else {
        document.getElementById('user').style.display = 'none';
        document.getElementById('guest').style.display = 'inline-block';
    }

    const loginLink = document.querySelector('#loginLink');
    const registerLink = document.querySelector('#registerLink');
    const catalogLink = document.querySelector('#catalogLink');
    const createLink = document.querySelector('#createLink');
    const publicationsLink = document.querySelector('#profileLink');

    loginLink.classList.remove('active');
    registerLink.classList.remove('active');
    catalogLink.classList.remove('active');
    createLink.classList.remove('active');
    publicationsLink.classList.remove('active');

    if (navType === 'login') {
        loginLink.classList.add('active');
    } else if (navType === 'register') {
        registerLink.classList.add('active');
    } else if (navType === 'catalog') {
        catalogLink.classList.add('active');
    } else if (navType === 'create') {
        createLink.classList.add('active');
    } else if (navType === 'publications') {
        publicationsLink.classList.add('active');
    } 
}

async function deleteSet(ctx) {
    console.log('deleteSet');

    const confirmed = confirm('Do you really want to delete this item?');

    if (!confirmed) {
        page.redirect('/catalog');
        return;
    }
    const id = ctx.params.id;
    await data.deleteFurnitureById(id);

    page.redirect('/catalog');
}

page(decoratorFunction);
page('/home', catalogSet);
page('/catalog', catalogSet);
page('/publications', catalogPublicationsSet);
page('/details/:id', catalogDetailsSet);
page('/create', createSet);
page('/edit/:id', editSet);
page('/delete/:id', deleteSet);
page('/login', loginSet);
page('/register', registerSet);

page.redirect('/','/home');
page.redirect('/index.html','/home');

page.start();

