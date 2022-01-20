function applyCreate(runCatalog) {

    const form = document.querySelector('#create form');

    form.addEventListener('submit', async function(e) {

        await createRecipe(e);

        runCatalog();

        const catalogBtn = document.querySelector('#catalogBtn');
        catalogBtn.className = "active";

        const createBtn = document.querySelector('#createBtn');
        createBtn.className = '';

        const articleCreate = document.querySelector('#create');
        articleCreate.style.display = 'none';
    });
    
    async function createRecipe(ev) {
    
        ev.preventDefault();
        const formData = new FormData(ev.target);
    
        const name = formData.get('name');
        const img = formData.get('img');
        const ingredients = formData.get('ingredients').split('\n')
        .map(l => l.trim()).filter(l => l != '');
        const preparationSteps = formData.get('steps').split('\n')
        .map(l => l.trim()).filter(l => l != '');
    
        const token = sessionStorage.getItem('authToken');

        await createRecipeRequest('http://localhost:3030/data/recipes'
        ,token, {name, img, ingredients, "steps":preparationSteps});
        
        // clear form
        ev.target.reset();
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

        } else {
            console.error(result.status);
        }
    
    }

    // show create page view
    const createArticle = document.querySelector('main #create');

    createArticle.style.display = "block";

}

export {applyCreate};

