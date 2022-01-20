import { html, until, data, getNavigation, getFooter, page} from '../lib.js';

const memeDetailsTemplate = (memesPromise) => html`
${getNavigation()}
<section id="meme-details">
            ${until(memesPromise, html`<p> Loading &hellip`)}
</section>
${getFooter()}         
`;

const catalogDetailsItemTemplate = (meme, onDelete) => {
    const imgSrc = meme.imageUrl.slice(0, 4) === 'http' ? meme.imageUrl : `${meme.imageUrl}`

    return html`
<h1>Meme Title: ${meme.title}
</h1>
<div class="meme-details">
                <div class="meme-img">
                    <img alt="meme-alt" src="${imgSrc}">
                </div>
                <div class="meme-description">
                    <h2>Meme Description</h2>
                    <p>
                        ${meme.description}
                    </p>
                    ${meme.isOwn ? html`
                    <!-- Buttons Edit/Delete should be displayed only for creator of this meme  -->
                    <a class="button warning" href="/edit/${meme._id}">Edit</a>
                    <button class="button danger" @click=${onDelete}>Delete</button>
                    `: ''}
                    
                </div>
</div>
`};



export async function memeDetailsSet(ctx) {
    console.log('memeDetailsSet');

    const id = ctx.params.id;
    ctx.render(memeDetailsTemplate(getMemeById(id,ctx)));
    ctx.updateNavigation('details');

}

async function getMemeById(id,ctx) {
    const meme = await data.getMemeById(id);
    meme.isOwn = meme._ownerId === sessionStorage.getItem('userId') ? true : false;

    async function onDelete(ev) {
        const confirmed = confirm('Do you really want to delete this item?');

        if (!confirmed) {
            // page.redirect('/memes');
            return;
        }
        const id = ctx.params.id;
        await data.deleteMemeById(id);

        page.redirect('/memes');
    }

    return catalogDetailsItemTemplate(meme, onDelete);
}