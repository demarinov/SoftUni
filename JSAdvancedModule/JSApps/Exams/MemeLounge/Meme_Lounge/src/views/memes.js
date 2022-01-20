import {html, until, data, getFooter, getNavigation} from '../lib.js';

const memesTemplate = (memePromise) => html`
${getNavigation()}
<section id="meme-feed">
<h1>All Memes</h1>
<div id="memes">
    <!-- Display : All memes in database ( If any ) -->
    ${until(memePromise, html `<h2>No memes in database.</h2>`)}
</div>
</section>    
${getFooter()}
`;

const memeItemTemplate = (meme) => html`
     <div class="meme">
        <div class="card">
            <div class="info">
                <p class="meme-title">${meme.title}</p>
                <img class="meme-image" alt="meme-img" src="${meme.imageUrl}">
            </div>
            <div id="data-buttons">
                <a class="button" href="/details/${meme._id}">Details</a>
            </div>
        </div>
    </div>
`;



export async function memeSet(ctx) {
    console.log('memeSet');

    ctx.render(memesTemplate(getAllMemes()));
    ctx.updateNavigation('meme');
}

// export async function catalogPublicationsSet(ctx) {
//     console.log('catalogPublicationsSet');

//     if (sessionStorage.getItem('userToken') != null) {
//         const userId = sessionStorage.getItem('userId');
//         ctx.render(catalogTemplate(getFurnituresByUserId(userId),true));

//     }

//     ctx.updateNavigation('publications');
// }

async function getAllMemes() {
    const memes = await data.getMemes();
    return memes.map(memeItemTemplate);
}

async function getFurnituresByUserId(userId) {
    const cards = await data.getFurnituresByUserId(userId);
    
    return cards.map(catalogItemTemplate);
}