
const form = document.querySelector('form');

form.addEventListener('submit', createRecipe);

function createRecipe(ev) {

    ev.preventDefault();
    const formData = new FormData(ev.target);

    const name = formData.get('name');
    const img = formData.get('img');
    const ingredients = formData.get('ingredients').split('\n')
    .map(l => l.trim()).filter(l => l != '');
    const preparationSteps = formData.get('steps').split('\n')
    .map(l => l.trim()).filter(l => l != '');

    console.log(ingredients);

    const token = sessionStorage.getItem('authToken');
    console.log(token);
    createRecipeRequest('http://localhost:3030/data/recipes'
    ,token, {name, img, ingredients, "steps":preparationSteps});

}

async function createRecipeRequest(url, token, data) {

    const result = await fetch(url, {
        method: 'post',
        headers: {
            "Content-Type":"application/json",
            "X-Authorization" : token
        },
        body : JSON.stringify(data)       
    });

    if (result.status === 200) {
        const response = await result.json();

        console.log(response);
        let path = window.location.pathname.replace(/create.html/,'index.html');
        window.location.pathname = path;
    } else {
        console.error(result.status);
    }

}