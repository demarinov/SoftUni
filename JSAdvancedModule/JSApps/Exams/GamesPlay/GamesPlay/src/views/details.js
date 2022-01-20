import { html, until, data, getNavigation, getFooter, page} from '../lib.js';
import {getComments, createCommentForm} from './comments.js';

const gameDetailsTemplate = (gamesPromise) => html`
${getNavigation()}
<section id="game-details">
            <h1>Game Details</h1>
            
            ${until(gamesPromise, html`<p> Loading &hellip`)}

        </section>    

`;

const gameDetailsItemTemplate = (game,commentsTemplate ,onDelete) => {

    return html`
    <div class="info-section">

                <div class="game-header">
                    <img class="game-img" src="${game.imageUrl}" />
                    <h1>${game.title}</h1>
                    <span class="levels">MaxLevel: ${game.maxLevel}</span>
                    <p class="type">${game.category}</p>
                </div>

                <p class="text">
                    ${game.summary}
                </p>

                ${game.isOwn ? html `
                <!-- Edit/Delete buttons ( Only for creator of this game )  -->
                <div class="buttons">
                    <a href="/edit/${game._id}" class="button">Edit</a>
                    <a @click=${onDelete} href="#delete" class="button">Delete</a>
                </div> ` :''}

                ${until(commentsTemplate, html `Loading comment &hellip;`)}
            </div>

            ${!game.isOwn && game.isLogged ? createCommentForm(game._id) : ''}

`};



export async function gameDetailsSet(ctx) {
    console.log('gameDetailsSet');

    const id = ctx.params.id;

    ctx.render(gameDetailsTemplate(getGameById(id,ctx)));
    ctx.updateNavigation('details');

}

async function getGameById(id,ctx) {
    const game = await data.getGameById(id);
    game.isOwn = game._ownerId === sessionStorage.getItem('userId') ? true : false;
    game.isLogged = sessionStorage.getItem('userId') != null ? true : false;

    async function onDelete(ev) {
        ev.preventDefault();
        const confirmed = confirm('Do you really want to delete this item?');

        if (!confirmed) {
            
            return;
        }
        const id = ctx.params.id;
        await data.deleteGameById(id);

        page.redirect('/home');
    }

    // bonus get comments
    const commentsTemplate = getComments(ctx.params.id);
    console.log(commentsTemplate);
    return gameDetailsItemTemplate(game,commentsTemplate, onDelete);
}