
// main app functionality
async function getRecipes() {
    const response = await fetch('http://localhost:3030/jsonstore/cookbook/recipes');
    const recipes = await response.json();

    return Object.values(recipes);
}

async function getAllRecipes(allUrl, singleUrl) {
    const response = await fetch('http://localhost:3030/data/recipes?select=_id%2Cname%2Cimg');
    const recipes = await response.json();

    return Object.values(recipes);

}

async function getRecipeByDataId(id) {
    const response = await fetch('http://localhost:3030/data/recipes/' + id);
    const recipes = await response.json();

    return recipes;

}

async function getRecipeById(id) {
    const response = await fetch('http://localhost:3030/jsonstore/cookbook/details/' + id);
    const recipe = await response.json();

    return recipe;
}

async function deleteRecipeById(section, id) {
    const token = sessionStorage.getItem('authToken');

    try {
        const response = await fetch('http://localhost:3030/data/recipes/' + id, {
            method: 'delete',
            headers: {
                'X-Authorization': token
            }
        });

        if (response.status != 200) {
            const error = await response.json();
            throw new Error(error.message);
        }

        section.innerHTML = '';
        section.appendChild(e('article', {}, e('h2', {}, 'Recipe deleted')));
    } catch (err) {
        alert(err.message);
    }
}

function createRecipePreview(recipe) {
    const result = e('article', { className: 'preview', onClick: toggleCard },
        e('div', { className: 'title' }, e('h2', {}, recipe.name)),
        e('div', { className: 'small' }, e('img', { src: recipe.img })),
    );

    return result;

    async function toggleCard() {
        const fullRecipe = await getRecipeByDataId(recipe._id);

        const recipeCardFull = createRecipeCard(fullRecipe);
        result.replaceWith(recipeCardFull);

        const catalogBtn = document.querySelector('#catalogBtn');
        catalogBtn.className = '';
        const articlesPreview = Array.from(document.querySelectorAll('.preview'));
        articlesPreview.forEach(ap => {
            ap.parentElement.removeChild(ap);
        });

    }
}

function createRecipeCard(recipe) {
    const result = e('article', { className: 'detail' },
        e('h2', {}, recipe.name),
        e('div', { className: 'band' },
            e('div', { className: 'thumb' }, e('img', { src: recipe.img })),
            e('div', { className: 'ingredients' },
                e('h3', {}, 'Ingredients:'),
                e('ul', {}, recipe.ingredients.map(i => e('li', {}, i))),
            )
        ),
        e('div', { className: 'description' },
            e('h3', {}, 'Preparation:'),
            recipe.steps.map(s => e('p', {}, s))
        ),
    );

    const userId = sessionStorage.getItem('userId');
    console.log(userId + ' ' + recipe._ownerId);
    if (userId != null && recipe._ownerId == userId) {
        result.appendChild(e('div', { className: 'controls' },
            e('button', { onClick: () => showEdit(result, recipe._id) }, '\u270E Edit'),
            e('button', { onClick: onDelete }, '\u2716 Delete'),
        ));
    }

    return result;

    function onDelete() {
        const confirmed = confirm(`Are you sure you want to delete ${recipe.name}?`);
        if (confirmed) {
            deleteRecipeById(result, recipe._id);
        }
    }

}

let recipeId;
let articleSection;

async function showEdit(dataSection, id) {
    setActiveNav();
    const main = document.querySelector('main');
    const section = document.querySelector('#edit');
    // main.innerHTML = '';
    main.appendChild(section);

    section.style.display = "block";

    recipeId = id;

    articleSection = dataSection;
    dataSection.style.display = 'none';
    const recipe = await getRecipeByDataId(recipeId);
    console.log(section);
    console.log('recipe: ' + JSON.stringify(recipe));
    section.querySelector('[name="name"]').value = recipe.name;
    section.querySelector('[name="img"]').value = recipe.img;
    section.querySelector('[name="ingredients"]').value = recipe.ingredients.join('\n');
    section.querySelector('[name="steps"]').value = recipe.steps.join('\n');
}

async function showDetails(id) {
    console.log('showDetails ' + articleSection);
    setActiveNav();
    let section = articleSection;
    section.innerHTML = 'Loading&hellip;';
    const main = document.querySelector('main');
    // main.innerHTML = '';
    // main.appendChild(section);
    main.removeChild(articleSection);

    const recipe = await getRecipeByDataId(id);
    section = createRecipeCard(recipe);
    main.appendChild(section);
    section.style.display = "block";
    main.querySelector('#edit').style.display = "none";
}

function setActiveNav(targetId) {
    const nav = document.querySelector('nav');
    [...nav.querySelectorAll('a')].forEach(a => a.id == targetId ? a.classList.add('active') : a.classList.remove('active'));
}

function setupEdit(targetMain, targetSection, onActiveNav) {
    setActiveNav = onActiveNav;
    const form = targetSection.querySelector('form');

    form.addEventListener('submit', (ev => {
        ev.preventDefault();
        const formData = new FormData(ev.target);
        onSubmit(targetSection, [...formData.entries()].reduce((p, [k, v]) => Object.assign(p, { [k]: v }), {}));
    }));

    async function onSubmit(targetSection, data) {
        const body = JSON.stringify({
            name: data.name,
            img: data.img,
            ingredients: data.ingredients.split('\n').map(l => l.trim()).filter(l => l != ''),
            steps: data.steps.split('\n').map(l => l.trim()).filter(l => l != '')
        });

        const token = sessionStorage.getItem('authToken');
        if (token == null) {
            return alert('You\'re not logged in!');
        }

        try {
            const response = await fetch('http://localhost:3030/data/recipes/' + recipeId, {
                method: 'put',
                headers: {
                    'Content-Type': 'application/json',
                    'X-Authorization': token
                },
                body
            });

            if (response.status == 200) {
                showDetails(recipeId);
            } else {
                const error = await response.json();
                throw new Error(error.message);
            }
        } catch (err) {
            alert(err.message);
            console.error(err.message);
        }
    }
}

async function logout(e) {
    console.log('logout');

    const token = sessionStorage.getItem('authToken');

    if (token != null) {
        await sendLogoutRequest('http://localhost:3030/users/logout', token);
    }

    runCatalog();

    // remove everything else
    const articleLogin = document.querySelector('#login');
    articleLogin.style.display = 'none';
    const loginBtn = document.querySelector('#loginBtn');
    loginBtn.className = '';

    const articleCreate = document.querySelector('#create');
    articleCreate.style.display = 'none';
    const logoutBtn = document.querySelector('#logoutBtn');
    logoutBtn.className = '';
    const createBtn = document.querySelector('#createBtn');
    createBtn.className = '';

    const articleRegister = document.querySelector('#register');
    articleRegister.style.display = 'none';
    const registerBtn = document.querySelector('#registerBtn');
    registerBtn.className = '';

    const catalogBtn = document.querySelector('#catalogBtn');
    catalogBtn.className = 'active';

}


async function sendLogoutRequest(url, token) {

    const result = await fetch(url, {
        method: 'get',
        headers: {
            'X-Authorization': token
        }

    });

    if (result.status >= 200 && result.status < 300) {
        // let path = window.location.pathname;
        // path = path.replace(/index.html/, 'index.html');
        sessionStorage.removeItem('authToken');
        sessionStorage.removeItem('userId');

        // window.location.pathname = path;

    } else {
        throw new Error(result.status);
    }

}

window.addEventListener('load', async () => {

    const main = document.querySelector('main');
    const nav = document.querySelector('nav');

    setupEdit(main, document.getElementById('edit'), setActiveNav);

    runCatalog();

    const catalogBtn = document.querySelector('#catalogBtn');

    catalogBtn.addEventListener('click', function (e) {

        e.preventDefault();

        // request and show recipes only
        runCatalog();

        catalogBtn.className = 'active';

        // remove or hide login, logout and register
        const articleLogin = document.querySelector('#login');
        articleLogin.style.display = 'none';
        const loginBtn = document.querySelector('#loginBtn');
        loginBtn.className = '';

        const articleCreate = document.querySelector('#create');
        articleCreate.style.display = 'none';
        const logoutBtn = document.querySelector('#logoutBtn');
        logoutBtn.className = '';
        const createBtn = document.querySelector('#createBtn');
        createBtn.className = '';

        const articleRegister = document.querySelector('#register');
        articleRegister.style.display = 'none';
        const registerBtn = document.querySelector('#registerBtn');
        registerBtn.className = '';

    });
});

async function runCatalog() {
    const main = document.querySelector('main');

    // remove or hide recipe articles
    // remove articles if any
    const articlesDetail = Array.from(document.querySelectorAll('.detail'));
    const articlesPreview = Array.from(document.querySelectorAll('.preview'));
    const articleEdit = document.querySelector('#edit');
    articleEdit.style.display = 'none';

    articlesDetail.forEach(ad => {
        ad.parentElement.removeChild(ad);
    });

    articlesPreview.forEach(ap => {
        ap.parentElement.removeChild(ap);
    });

    if (sessionStorage.getItem('authToken') != null) {
        document.getElementById('user').style.display = 'inline-block';
        document.getElementById('logoutBtn').removeEventListener('click', logout);
        document.getElementById('logoutBtn').addEventListener('click', logout);
        document.getElementById('guest').style.display = 'none';
    } else {
        document.getElementById('guest').style.display = 'inline-block';
        document.getElementById('user').style.display = 'none';
    }

    const recipes = await getAllRecipes();
    const cards = recipes.map(createRecipePreview);

    // main.innerHTML = '';
    if (cards.length > 0) {
        document.querySelector('main p').style.display = 'none';
    } else {
        document.querySelector('main p').style.display = 'block';
    }
    cards.forEach(c => main.appendChild(c));
}

function e(type, attributes, ...content) {
    const result = document.createElement(type);

    for (let [attr, value] of Object.entries(attributes || {})) {
        if (attr.substring(0, 2) == 'on') {
            result.addEventListener(attr.substring(2).toLocaleLowerCase(), value);
        } else {
            result[attr] = value;
        }
    }

    content = content.reduce((a, c) => a.concat(Array.isArray(c) ? c : [c]), []);

    content.forEach(e => {
        if (typeof e == 'string' || typeof e == 'number') {
            const node = document.createTextNode(e);
            result.appendChild(node);
        } else {
            if (e !== undefined) {
                result.appendChild(e);
            }
        }
    });

    return result;
}

// end of main app functionality

// login module startup
import * as login from './login.js';

const loginBtn = document.querySelector('#loginBtn');

loginBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const main = document.querySelector('main');

    // remove or hide articles
    const articlesDetail = Array.from(document.querySelectorAll('.detail'));
    const articlesPreview = Array.from(document.querySelectorAll('.preview'));

    articlesDetail.forEach(ad => {
        ad.parentElement.removeChild(ad);
    });

    articlesPreview.forEach(ap => {
        ap.parentElement.removeChild(ap);
    });

    e.target.className = "active";
    const catalogBtn = document.querySelector('#catalogBtn');
    catalogBtn.className = "";

    // hide register article
    const registerArticle = document.querySelector('#register');
    registerArticle.style.display = 'none';

    const registerBtn = document.querySelector('#registerBtn');
    registerBtn.className = '';

    login.applyLogin(runCatalog);

    // createLoginPage

});

// create recipe flow
import * as create from "./create.js";

const createBtn = document.querySelector('#createBtn');

createBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const main = document.querySelector('main');

    // remove or hide articles
    const articlesDetail = Array.from(document.querySelectorAll('.detail'));
    const articlesPreview = Array.from(document.querySelectorAll('.preview'));

    articlesDetail.forEach(ad => {
        ad.parentElement.removeChild(ad);
    });

    articlesPreview.forEach(ap => {
        ap.parentElement.removeChild(ap);
    });

    e.target.className = "active";
    const catalogBtn = document.querySelector('#catalogBtn');
    catalogBtn.className = "";

    create.applyCreate(runCatalog);
});

// register flow
import * as register from "./register.js";

const registerBtn = document.querySelector('#registerBtn');

registerBtn.addEventListener('click', function (e) {

    e.preventDefault();
    const main = document.querySelector('main');

    // remove or hide articles
    const articlesDetail = Array.from(document.querySelectorAll('.detail'));
    const articlesPreview = Array.from(document.querySelectorAll('.preview'));

    articlesDetail.forEach(ad => {
        ad.parentElement.removeChild(ad);
    });

    articlesPreview.forEach(ap => {
        ap.parentElement.removeChild(ap);
    });

    e.target.className = "active";
    const catalogBtn = document.querySelector('#catalogBtn');
    catalogBtn.className = "";

    // hide login article
    const loginArticle = document.querySelector('#login');
    loginArticle.style.display = 'none';

    const loginBtn = document.querySelector('#loginBtn');
    loginBtn.className = '';

    register.applyRegister(runCatalog);
});


