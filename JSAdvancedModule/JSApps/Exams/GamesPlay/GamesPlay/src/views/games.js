import {html, until, data, getFooter, getNavigation} from '../lib.js';

const gamesTemplate = (gamesPromise) => html`
${getNavigation()}
<section id="catalog-page">
            <h1>All Games</h1>
            <!-- Display div: with information about every game (if any) -->
            
                 ${until(gamesPromise, html `<p>Loading games &hellip;</p>`)}            
`;

export const gameItemTemplate = (game) => html`
<div class="allGames">
                <div class="allGames-info">
                    <img src="${game.imageUrl}">
                    <h6>${game.category}</h6>
                    <h2>${game.title}</h2>
                    <a href="/details/${game._id}" class="details-button">Details</a>
                </div>

</div>
`;


export async function gameSet(ctx) {
    console.log('gameSet');

    ctx.render(gamesTemplate(getAllGames()));
    ctx.updateNavigation('game');
}

async function getAllGames() {
    const games = await data.getGames();

    if (games == null || games.length === 0){

        return html `<h2>No articles yet</h2>`;
    }

    return games.map(gameItemTemplate);
}
