import * as requests from "./requests.js";
import * as utils from "./utils.js";
import * as home from "./home.js";
import * as likes from "./likes.js";

// may be needed for pagination
async function getNumberOfMovies() {

    const response = await fetch('http://localhost:3030/data/movies?count', {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

// •	Get all movies: /data/movies (GET)
async function getMovies() {

    const response = await fetch('http://localhost:3030/data/movies', {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

async function getMovieByTitle(title) {

    const response = await fetch(`http://localhost:3030/data/movies?where=title%3D%22${title}%22`, {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

async function getMovieById(id) {

    const response = await fetch(`http://localhost:3030/data/movies/${id}`, {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

// •	Create movie: /data/movies (POST)
async function createMovie(data) {

    const token = sessionStorage.getItem('authToken');

    let error = false;
    const response = await requests.createMovieRequest(data, token)
        .catch(e => {
            error = true;
            console.error(e);
        });

    return [error, response];
}

// •	Update movie: /data/movies/:id (PUT)
async function updateMovie(data, id) {

    await requests.sendUpdateMoviePutRequest(data,id);
}
// •	Delete movie: /data/movies/:id (DELETE)
async function deleteMovie(id) {

    await requests.sendDeleteMovieRequest(id);
}

let containerMain;
let addMovieMain;
let movieSection;
let movieDetailSection;
let editMovieMain;
function setupMovies(main) {

    containerMain = main;
    addMovieMain = main.querySelector('#add-movie');
    movieSection = main.querySelector('#movie');
    movieDetailSection = main.querySelector('#movie-example');
    editMovieMain = main.querySelector('#edit-movie');
    setupMovieHandlers();
}

function addMovieForm(form) {


    form.addEventListener('submit', async function (e) {

        e.preventDefault();

        const formData = new FormData(form);

        const title = formData.get('title');
        const description = formData.get('description');
        const img = formData.get('imageUrl');

        if (title === ''
            || description === ''
            || img === '') {
            console.error('Empty input field/s!');
            return;
        }

        // send post login request
        const data = {
            title,
            description,
            img
        };

        let [error, response] = await createMovie(data);

        // clear form
        utils.clearForm(form);

        if (error) {
            console.error('Cannot create movie!');
            return;
        }

        // show home page - user logged in
        home.showHome();
    });


}

function showMovieForm() {

    // hide everything except add-movie form and nav login
    containerMain.querySelector('#home-page').style.display = 'none';
    containerMain.querySelector('h1:nth-child(3)').style.display = 'none';
    containerMain.querySelector('#add-movie-button').style.display = 'none';
    containerMain.querySelector('#movie').style.display = 'none';
    containerMain.querySelector('#movie-example').style.display = 'none';
    containerMain.querySelector('#form-sign-up').style.display = 'none';
    containerMain.querySelector('#form-login').style.display = 'none';
    containerMain.querySelector('#edit-movie').style.display = 'none';

    // show login form
    containerMain.querySelector('#add-movie').style.display = 'block';
}

function setupMovieHandlers() {

    const addMovieBtn = containerMain.querySelector('#add-movie-button');
    addMovieBtn.addEventListener('click', function (e) {

        // show movie form
        showMovieForm();
    });

    // add movie handler
    addMovieForm(addMovieMain.querySelector('form'));
}

async function showMovies() {
    // show all movies from server, re-render ...
    const subMovieContainer = movieSection.querySelector('div>div>div');
    // fetch all movies from server
    let movies = await getMovies();

    subMovieContainer.innerHTML = '';

    movies.forEach(m => {

        const innerHtml = `
        <div class="card mb-4">
            <img class="card-img-top" src=${m.img}
                 alt="Card image cap" width="400">
            <div class="card-body">
                <h4 class="card-title">${m.title}</h4>
            </div>
            <div class="card-footer">
                <a href="#">
                    <button type="button" class="btn btn-info">Details</button>
                </a>
            </div>
        </div>`;
        
        subMovieContainer.innerHTML += innerHtml;
    });

    const domMovieDetailsBtns = Array.from(subMovieContainer.querySelectorAll('.card a'));

    let movieCount = 0;
    domMovieDetailsBtns.forEach(db => {
        const boundedMovieDetails = movieDetails.bind(movies[movieCount++]);
        db.addEventListener('click', boundedMovieDetails);
    });
}

async function movieDetails(e) {
    e.preventDefault();

    if (sessionStorage.getItem('authToken') === null) {
        console.log('Only logged-in users can view movie details!');
        alert('Only logged-in users can view movie details');
        return;
    }

    const movie = this;

    showMovieDetails(movie);

}

async function showMovieDetails(movie) {

    // hide / remove anything else
    containerMain.querySelector('#home-page').style.display = 'none';
    containerMain.querySelector('h1:nth-child(3)').style.display = 'none';
    containerMain.querySelector('#add-movie-button').style.display = 'none';
    containerMain.querySelector('#movie').style.display = 'none';
    containerMain.querySelector('#add-movie').style.display = 'none';
    containerMain.querySelector('#form-login').style.display = 'none';
    containerMain.querySelector('#form-sign-up').style.display = 'none';
    containerMain.querySelector('#edit-movie').style.display = 'none';

    // show movie details
    const movieDetailSubSection = movieDetailSection.querySelector('.container');
    const innerHtml = `<div class="row bg-light text-dark">
    <h1>Movie title: ${movie.title}</h1>

    <div class="col-md-8">
        <img class="img-thumbnail" src=${movie.img}
             alt="Movie">
    </div>
    <div class="col-md-4 text-center">
        <h3 class="my-3 ">Movie Description</h3>
        <p>${movie.description}</p>
        <a class="btn btn-danger" href="#">Delete</a>
        <a class="btn btn-warning" href="#">Edit</a>
        <a class="btn btn-primary" href="#">Like</a>
        <span class="enrolled-span">Liked 1</span>
    </div>
</div>`;

    movieDetailSection.style.display = 'block';
    movieDetailSubSection.innerHTML = '';
    movieDetailSubSection.innerHTML = innerHtml;

    // if creator show edit and delete otherwise show like button

    const userId = sessionStorage.getItem('userId');

    if (userId === movie['_ownerId']) {
        const el1 = movieDetailSubSection.querySelector('.col-md-4 a:nth-child(5)');

        const el2 = movieDetailSubSection.querySelector('.col-md-4 span:nth-child(6)');
        el1.parentElement.removeChild(el1);
        el2.parentElement.removeChild(el2);

        // delete details btn handler
        const deleteDetailsBtn = movieDetailSubSection
            .querySelector('.col-md-4 a:nth-child(3)');

        deleteDetailsBtn.addEventListener('click', function (e) {
            e.preventDefault();

            deleteDetails(movie['_id']);
        });

        // edit details btn handler
        const editDetailsBtn = movieDetailSubSection
            .querySelector('.col-md-4 a:nth-child(4)');

        editDetailsBtn.addEventListener('click', function (e) {
            e.preventDefault();

            editDetails(movie);
        });

    } else {

        const likeBtn = movieDetailSubSection
        .querySelector('.col-md-4 a:nth-child(5)');
        const el1 = movieDetailSubSection.querySelector('.col-md-4 a:nth-child(3)');

        const el2 = movieDetailSubSection.querySelector('.col-md-4 a:nth-child(4)');
        const el3 = movieDetailSubSection.querySelector('.col-md-4 span:nth-child(6)');
        
        el1.parentElement.removeChild(el1);
        el2.parentElement.removeChild(el2);

        // like movie btn handler
        likeBtn.addEventListener('click', function(e) {
            e.preventDefault();

            likeMovie(movie);
        });

        const userId = sessionStorage.getItem('userId');
        if (sessionStorage.getItem('liked:'+movie['_id']+':'+userId)) {
            likeBtn.parentElement.removeChild(likeBtn);
            const countLikesPerUser = await likes
            .getLikesByMovieId(movie['_id']);

            el3.textContent = 'Liked ' + countLikesPerUser;

        } else {
            el3.parentElement.removeChild(el3);
        }

    }
}

async function likeMovie(movie) {
    console.log('likeMovie()');

    // make post request
    const body = {
        movieId: movie['_id']
    }

    await likes.addLike(body);

    // mark item liked by user
    const userId = sessionStorage.getItem('userId');
    sessionStorage.setItem('liked:'+movie['_id']+':'+userId,true);

    showMovieDetails(movie);
}

function showEditForm(movie) {

    // show edit form and hide everything else
    // hide / remove anything else
    containerMain.querySelector('#home-page').style.display = 'none';
    containerMain.querySelector('h1:nth-child(3)').style.display = 'none';
    containerMain.querySelector('#add-movie-button').style.display = 'none';
    containerMain.querySelector('#movie').style.display = 'none';
    containerMain.querySelector('#add-movie').style.display = 'none';
    containerMain.querySelector('#form-login').style.display = 'none';
    containerMain.querySelector('#form-sign-up').style.display = 'none';
    containerMain.querySelector('#movie-example').style.display = 'none';

    // show edit form
    containerMain.querySelector('#edit-movie').style.display = 'block';

    // prepopulate from movie obj
    const editForm = containerMain.querySelector('#edit-movie form');
    editForm.querySelector('input[name=title]').value = movie.title;
    editForm.querySelector('textarea[name=description]').value = movie.description;
    editForm.querySelector('input[name=imageUrl]').value = movie.img;

}

async function editDetails(movie) {

    const movieId = movie['_id'];
    // show edit form
    showEditForm(movie);

    // edit form submit handler
    const editForm = editMovieMain.querySelector('form');

    editForm.addEventListener('submit', async function (e) {
        e.preventDefault();

        const formData = new FormData(editForm);

        const title = formData.get('title');
        const img = formData.get('imageUrl');
        const description = formData.get('description');

        const data = {
            title,
            img,
            description,
            _id: movie['_id'],
            _ownerId: movie['_ownerId']
        }

        await updateMovie(data, movieId);

        // update movie obj
        movie.title = title;
        movie.img = img;
        movie.description = description;

        // show details page
        showMovieDetails(movie);
    })




}

async function deleteDetails(movieId) {

    await deleteMovie(movieId);

    // show homePage
    home.showHome();
}

export { getMovies, setupMovies, showMovies };
