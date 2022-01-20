import {page, render, html, utils} from './lib.js';
import {catalogSet} from './views/catalog.js';
import {homeSet} from './views/home.js';
import {loginSet} from './views/login.js';
import {registerSet} from './views/register.js';
import {albumDetailsSet} from './views/details.js';
import {createSet} from './views/create.js';
import {editSet} from './views/edit.js';
import {searchSet} from './views/search.js';
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

    if (sessionStorage.getItem('userToken') != null) {
        // user space
        document.querySelector('ul li a[href="/create"]').style.display = 'inline-block';
        document.querySelector('ul li a[href="/logout"]').style.display = 'inline-block';

        // guest space
        document.querySelector('ul li a[href="/login"]').style.display = 'none';
        document.querySelector('ul li a[href="/register"]').style.display = 'none';

    } else {
        
        // user space
        document.querySelector('ul li a[href="/create"]').style.display = 'none';
        document.querySelector('ul li a[href="/logout"]').style.display = 'none';

        // guest space
        document.querySelector('ul li a[href="/login"]').style.display = 'inline-block';
        document.querySelector('ul li a[href="/register"]').style.display = 'inline-block';

    
    }

    // common
    document.querySelector('ul li a[href="/catalog"]').style.display = 'inline-block';
    document.querySelector('ul li a[href="/search"]').style.display = 'inline-block';



}


// Routing ....
console.log('routing')
page(decoratorFunction);
page('/home', homeSet);
page('/catalog', catalogSet);
page('/login', loginSet);
page('/register', registerSet);
page('/create', createSet);
page('/logout', logout);
page('/details/:id', albumDetailsSet);
page('/edit/:id', editSet);
page('/search', searchSet);



page.redirect('/','/home');
page.redirect('/index.html','/home');

page.start();

