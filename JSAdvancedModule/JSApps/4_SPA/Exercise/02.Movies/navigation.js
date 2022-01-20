import * as user from "./user.js";

function doNavigation() {
    console.log('doNavigation()');

    if (sessionStorage.getItem('authToken') != null) {
        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(1)')
        .style.display = 'flex';

        const userMail = sessionStorage.getItem('userEmail');

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(1) a')
        .textContent = `Welcome, ${userMail}`;

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(2)')
        .style.display = 'flex';

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(3)')
        .style.display = 'none';

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(4)')
        .style.display = 'none';
    } else {
        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(1)')
        .style.display = 'none';

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(2)')
        .style.display = 'none';

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(3)')
        .style.display = 'inline-block';

        navigationBar.querySelector('.navbar-nav .nav-item:nth-child(4)')
        .style.display = 'inline-block';
    }

    
}

function doNavigationHandlers() {

    // register user setup
    const registerBtn = navigationBar
    .querySelector('.navbar-nav .nav-item:nth-child(4)');

    registerBtn.addEventListener('click', user.register);

    // login user setup
    const loginBtn = navigationBar
    .querySelector('.navbar-nav .nav-item:nth-child(3)');

    loginBtn.addEventListener('click', user.login);

    // logout user setup
    const logoutBtn = navigationBar
    .querySelector('.navbar-nav .nav-item:nth-child(2)');

    logoutBtn.addEventListener('click', user.logout);
}

let containerMain;
let navigationBar;
function setupNav(main) {
    containerMain = main;
    navigationBar = document.querySelector('.navbar');

    doNavigationHandlers();
}

export {doNavigation, doNavigationHandlers, setupNav};