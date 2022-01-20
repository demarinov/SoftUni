import { e } from '../dom.js';
import { getRecent } from '../api/data.js';
import * as templates from "./templates.js";
import { html,render } from '../../../../../node_modules/lit-html/lit-html.js';

// render does not render subsequently since the same container used
// should make composite and then render once or have different container...
export function setupHome(section, nav) {
    const container = section.querySelector('.recent-recipes');
    return showHome;

    async function showHome() {
        container.innerHTML = 'Loading&hellip;';

        const recipes = await getRecent();
        const cards = recipes.map(createRecipePreview);

        const cardsTemplate = templates.createRecipesTemplate(recipes,nav);

        const fragment = document.createDocumentFragment();


        if (cardsTemplate !== null) {
            render(cardsTemplate, fragment);
            fragment.appendChild(createSpacer());
        }
        container.innerHTML = '';
        container.appendChild(fragment);

        return section;
    }

    function createRecipePreview(recipe) {
        const result = e('article', { className: 'recent', onClick: () => nav.goTo('details', recipe._id) },
            e('div', { className: 'recent-preview' }, e('img', { src: recipe.img })),
            e('div', { className: 'recent-title' }, recipe.name),
        );

        return result;
    }

    function createSpacer() {
        return e('div', { className: 'recent-space' });
    }

}