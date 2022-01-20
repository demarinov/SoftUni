// in general it is better to have login and register views separately...

import * as utils from "./utils.js";
import * as requests from './requests.js';
import * as home from "./home.js";
import * as nav from "./navigation.js";

let containerMain;
let userLoginMain;
let registerMain;
function setupUserView(main) {
    containerMain = main;
    userLoginMain = main.querySelector('#form-login');
    registerMain = main.querySelector('#form-sign-up');

    setupUserHandlers();
}

async function logout(e) {

    console.log('logout()');

    const token = sessionStorage.getItem('authToken');

    if (token != null) {
        await requests.sendLogoutRequest(token);
    }

    // show login page
    login(null);
    nav.doNavigation();
}

function login(e) {
    console.log('login()');

    if (e != null) {
        e.preventDefault();
    }

    // hide everything except login form and nav no login
    containerMain.querySelector('#home-page').style.display = 'none';
    containerMain.querySelector('h1:nth-child(3)').style.display = 'none';
    containerMain.querySelector('#add-movie-button').style.display = 'none';
    containerMain.querySelector('#movie').style.display = 'none';
    containerMain.querySelector('#add-movie').style.display = 'none';
    containerMain.querySelector('#movie-example').style.display = 'none';
    containerMain.querySelector('#form-sign-up').style.display = 'none';
    containerMain.querySelector('#edit-movie').style.display = 'none';

    // show login form
    userLoginMain.style.display = 'block';

}

function register(e) {
    console.log('register()');

    if (e != null) {
        e.preventDefault();
    }
    // hide everything except registration form and nav no login
    containerMain.querySelector('#home-page').style.display = 'none';
    containerMain.querySelector('h1:nth-child(3)').style.display = 'none';
    containerMain.querySelector('#add-movie-button').style.display = 'none';
    containerMain.querySelector('#movie').style.display = 'none';
    containerMain.querySelector('#add-movie').style.display = 'none';
    containerMain.querySelector('#movie-example').style.display = 'none';
    containerMain.querySelector('#form-login').style.display = 'none';
    containerMain.querySelector('#edit-movie').style.display = 'none';

    // show registration form
    registerMain.style.display = 'block';
}

function registerUserForm(form) {


    form.addEventListener('submit',async function (e) {

        e.preventDefault();

        const formData = new FormData(form);

        const email = formData.get('email');
        const password = formData.get('password');
        const repeatPassword = formData.get('repeatPassword');

        if (email === ''
            || password === ''
            || repeatPassword === '') {
            console.error('Empty input field/s!');
            return;
        }

        // send post registration request
        const data = {
            email,
            password,
            repeatPassword
        };
        
        let error = false;
        await requests.sendRegistrationPostRequest(data).catch(e => {
            error = true;
            console.error(e);
        });

        // clear form
        utils.clearForm(form);
        
        if (error) {
            return;
        }

        // show home page - user logged in
        home.showHome();
    });


}

function loginUserForm(form) {


    form.addEventListener('submit',async function (e) {

        e.preventDefault();

        const formData = new FormData(form);

        const email = formData.get('email');
        const password = formData.get('password');

        if (email === ''
            || password === '') {
            console.error('Empty input field/s!');
            return;
        }

        // send post login request
        const data = {
            email,
            password
        };
        
        let error = false;
        await requests.sendLoginPostRequest(data).catch(e => {
            error = true;
            console.error(e);
        });

        // clear form
        utils.clearForm(form);
        
        if (error) {
            return;
        }

        // show home page - user logged in
        home.showHome();
    });


}

function setupUserHandlers() {

    // registration handler
    registerUserForm(registerMain.querySelector('form'));

    // login handler
    loginUserForm(userLoginMain.querySelector('form'));
}


export { login, register, setupUserView, logout};