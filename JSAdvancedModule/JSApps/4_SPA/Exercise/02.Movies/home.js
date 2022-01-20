
import * as nav from "./navigation.js";
import * as user from "./user.js";
import * as movies from "./movies.js";

let main;
function doHome() {
    console.log('doHome()');

    main = document.querySelector('#container');
    nav.setupNav(main);
    nav.doNavigation();

    setupHomeHandlers();

    user.setupUserView(main);
    movies.setupMovies(main);

    showHome();

    
}

function showHome() {

    // show only home page and movies
    // hide everything except registration form and nav no login
    main.querySelector('#form-sign-up').style.display = 'none';
    main.querySelector('#add-movie').style.display = 'none';
    main.querySelector('#movie-example').style.display = 'none';
    main.querySelector('#form-login').style.display = 'none';
    main.querySelector('#edit-movie').style.display = 'none';

    // show home page
    main.querySelector('#home-page').style.display = 'block';
    main.querySelector('h1:nth-child(3)').style.display = 'block';

    if (sessionStorage.getItem('authToken') != null) {
        main.querySelector('#add-movie-button').style.display = 'block';
    } else {
        main.querySelector('#add-movie-button').style.display = 'none';
    }
    main.querySelector('#movie').style.display = 'block';

    nav.doNavigation();

    movies.showMovies();
}

function setupHomeHandlers() {
    const movieBtn = main.querySelector('nav .navbar-brand');

    movieBtn.addEventListener('click', function(e) {
        e.preventDefault();
        showHome();
        nav.doNavigation();
    });
}

export {doHome, showHome};