import { createNav } from './navigation.js';
import { logout as apiLogout } from './api/data.js';

import { setupHome } from './views/home.js';
import { setupCatalog } from './views/catalog.js';
import { setupCreate } from './views/create.js';
import { setupLogin } from './views/login.js';
import { setupRegister } from './views/register.js';
import { setupDetails } from './views/details.js';
import { setupEdit, setupDeleted } from './views/edit.js';

import page from "/node_modules/page/page.mjs";


window.addEventListener('load', async () => {
    // const main = document.querySelector('main');
    // const navbar = document.querySelector('nav');
    // const navigation = createNav(main, navbar);

    // navigation.registerView('home', setupHome);
    // navigation.registerView('catalog', setupCatalog, 'catalogLink');
    // navigation.registerView('details', setupDetails);
    // navigation.registerView('login', setupLogin, 'loginLink');
    // navigation.registerView('register', setupRegister, 'registerLink');
    // navigation.registerView('create', setupCreate, 'createLink');
    // navigation.registerView('edit', setupEdit);
    // navigation.registerView('deleted', setupDeleted);

    // navigation.setUserNav();
    document.getElementById('logoutBtn').addEventListener('click', logout);

    // Start application in catalog view
    // navigation.goTo('home');


    async function logout() {
        try {
            await apiLogout();
            // navigation.setUserNav();
            // navigation.goTo('catalog');
            page.redirect('/catalog');
        } catch (err) {
            alert(err.message);
        }
    }

    // page.redirect('*', './home');
    // page.redirect('./home');
});

const main = document.querySelector('main');
const navbar = document.querySelector('nav');
const navigation = createNav(main, navbar);

navigation.registerView('home', setupHome);
navigation.registerView('catalog', setupCatalog, 'catalogLink');
navigation.registerView('details', setupDetails);
navigation.registerView('login', setupLogin, 'loginLink');
navigation.registerView('register', setupRegister, 'registerLink');
navigation.registerView('create', setupCreate, 'createLink');
navigation.registerView('edit', setupEdit);
navigation.registerView('deleted', setupDeleted);


page('/login', () => {
    navigation.setUserNav();
    console.log('login callback');
    navigation.goTo('login');
})

// testing, learning demo
page('/catalog', function (e) {

    navigation.setUserNav();
    // document.getElementById('logoutBtn').addEventListener('click', logout);
    console.log('catalog callback');
    navigation.goTo('catalog');

});

page('/register', () => {

    navigation.setUserNav();
    console.log('register');
    // navigation.setUserNav();
    navigation.goTo('register');
});

page('/home', () => {

    navigation.setUserNav();
    console.log('home');
    // navigation.setUserNav();
    navigation.goTo('home');
});

page('/create', () => {

    navigation.setUserNav();
    console.log('create recipe');
    // navigation.setUserNav();
    navigation.goTo('create');

})

// page('/logout', () => {

//     console.log('logout');
//     // navigation.setUserNav();

//     async function logout() {
//         try {
//             await apiLogout();
//             navigation.setUserNav();
//             navigation.goTo('catalog');

//         } catch (err) {
//             alert(err.message);
//         }
//     }

//     logout();



// });





page.redirect('/', '/home');
page.redirect('*/index.html','./home');

page.start();
