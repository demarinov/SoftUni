function loadRecipes() {

    const main = document.querySelector('main');

    const urlGetAll = 'http://localhost:3030/jsonstore/cookbook/recipes';


    fetch(urlGetAll)
        .then(response => response.json())
        .then(data => {

            recipesLoaded(data);

            function recipesLoaded(recipes) {


                main.innerHTML = '';
                for (const recipeKey in recipes) {
 
                    const article = document.createElement('article');
                    const recipeSection = createRecipe(recipes[recipeKey]);

                    article.appendChild(recipeSection[0]);
                    article.appendChild(recipeSection[1]);
                    article.className = 'preview';

                    clickArticle(article, recipes[recipeKey]);

                    main.appendChild(article);
                };

                function clickArticle(article, recipe) {

                    showIngredients(article, recipe);

                }

                function showIngredients(article, recipe) {

                    // fetch and show recipe ingredients
                    article.addEventListener('click', function (e) {

                        const recipeUrl = 'http://localhost:3030/jsonstore/cookbook/details/'
                            + recipe['_id'];

                        fetch(recipeUrl)
                            .then(response => response.json())
                            .then(data => {

                                showRecipe(data);

                                function showRecipe(data) {

                                    const h3 = document.createElement('h3');
                                    const h2 = document.createElement('h2');
                                    const ul = document.createElement('ul');
                                    const img = document.createElement('img');
                                    const divBand = document.createElement('div');
                                    const divImg = document.createElement('div');
                                    const divIngre = document.createElement('div');
                                    divIngre.className = 'ingredients';
                                    divImg.className = 'thumb';
                                    divBand.className = 'band';    

                                    h2.textContent = data.name;
                                    h3.textContent = 'Ingredients:';
                                    img.src = data.img;
                                    divImg.appendChild(img);
                                    divIngre.appendChild(h3);
                                
                                    for (const ingredient of data.ingredients) {
                                        const li = document.createElement('li');

                                        li.textContent = ingredient;
                                        ul.appendChild(li);
                                    }

                                    divIngre.appendChild(ul);

                                    const subDiv = document.createElement('div');
                                    subDiv.className = 'description';
                                    const headerPrep = document.createElement('h3');

                                    headerPrep.textContent = 'Preparation:';
                                    subDiv.appendChild(headerPrep);
                                    for (const step of data.steps) {
                                        const p = document.createElement('p');

                                        p.textContent = step;
                                        subDiv.appendChild(p);
                                    }

                                    main.innerHTML = '';
                                    const article = document.createElement('article');
                                    
                                    article.appendChild(h2);
                                    article.appendChild(divBand);
                                    divBand.appendChild(divImg);
                                    divBand.appendChild(divIngre);
                                    article.appendChild(subDiv);

                                    main.appendChild(article);
                                }
                            })
                            .catch(e => console.log(e))
                    });
                }

                function createRecipe(recipe) {
                    const h = document.createElement('h2');
                    const divH = document.createElement('div');
                    const img = document.createElement('img');
                    const divImg = document.createElement('div');;

                    h.textContent = recipe.name;
                    divH.className = 'title';
                    divH.appendChild(h);
                    img.src = recipe.img;
                    divImg.className = 'small';
                    divImg.appendChild(img);

                    return [divH, divImg];
                }

            }
        })
        .catch(e => console.log(e));

}


window.addEventListener('load', function (e) {
    // e.preventDefault();
    loadRecipes();
});
