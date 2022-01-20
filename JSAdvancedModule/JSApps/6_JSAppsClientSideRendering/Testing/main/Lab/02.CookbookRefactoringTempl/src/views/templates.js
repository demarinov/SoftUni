// import { html,render } from '../../../../../node_modules/lit-html/lit-html.js';
import { html,render } from 'https://unpkg.com/lit-html?module';

const createRecipeCardTemplate = (recipe, section,nav) => {
    console.log(recipe.name);
    console.log(recipe.img);
    console.log(recipe.ingredients);
    console.log(recipe.steps);
    const recipeCardTemplate = html`<article>
        <h2>${recipe.name}</h2>
        <div class="band">
            <div class="thumb">
                <img src="${recipe.img}">
            </div>
            <div class="ingredients">
                <h3>Ingredients:</h3>
                <ul>
                ${recipe.ingredients.map(ingre => html`
                    <li>${ingre}</li>`
                )}
                </ul>
            </div>
        </div>
        <div class="description">
            <h3>Preparation:</h3>
            ${recipe.steps.map(step =>
            html`    
            <p>${step}</p>`
            )}
        </div>
        
    </article>`;
    // ${getUserControls(recipe,nav, section)}
    return recipeCardTemplate;

    function getUserControls(recipe,nav, section) {
        const userId = sessionStorage.getItem('userId');
        if (userId != null && recipe._ownerId == userId) {
            console.log('user control');
            const userControlTemplate = html `
                <div class="controls">
                    <button @click=${(ev) => nav.goTo('edit',recipe._id)}>
                        \u270E Edit
                    </button>
                    <button @click=${onDelete}>
                        \u2716 Delete
                    </button>
                </div>
            `;

            // result.appendChild(e('div', { className: 'controls' },
            //     e('button', { onClick: () => nav.goTo('edit', recipe._id) }, '\u270E Edit'),
            //     e('button', { onClick: onDelete }, '\u2716 Delete'),
            // ));

            return userControlTemplate;

            async function onDelete() {
                const confirmed = confirm(`Are you sure you want to delete ${recipe.name}?`);
                if (confirmed) {
                    try {
                        await deleteRecipeById(recipe._id);
                        section.innerHTML = '';
                        section.appendChild(e('article', {}, e('h2', {}, 'Recipe deleted')));
                    } catch (err) {
                        alert(err.message);
                    }
                }
            }
        }

        return html ``;
    };
}

function createRecipesTemplate(recipes, nav) {
    console.log('Recipes Template');
    if (recipes.length > 0) {
        return html`
            ${recipes.map((recipe) => {
            // template section
            const resultTemplate = (data) => {
                
                return html`
         <article class="recent" @click=${(ev) => nav.goTo('details', data._id)}>
             <div class="recent-preview">
                 <img src="${data.img}">
             </div>
             <div class="recent-title">${data.name}</div>
         </article> `;
            };
            return resultTemplate(recipe);
        })}`;
    }

    return null;
}

export {createRecipeCardTemplate, createRecipesTemplate};