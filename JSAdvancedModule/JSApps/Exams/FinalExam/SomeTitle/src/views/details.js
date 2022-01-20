import { html, until, data, getNavigation, getFooter, page} from '../lib.js';

const albumDetailsTemplate = (albumsPromise) => html`
${getNavigation()}
<section id="detailsPage">
        ${until(albumsPromise, html`<p> Loading &hellip`)}
</section>      

${getFooter()}

`;

const albumDetailsItemTemplate = (album,onDelete) => {

    return html`
    <div class="wrapper">
                <div class="albumCover">
                    <img src="${album.imgUrl}">
                </div>
                <div class="albumInfo">
                    <div class="albumText">

                        <h1>Name: ${album.name}</h1>
                        <h3>Artist: ${album.artist}</h3>
                        <h4>Genre: ${album.genre}</h4>
                        <h4>Price: $${album.price}</h4>
                        <h4>Date: ${album.releaseDate}</h4>
                        <p>${album.description}</p>
                    </div>

                    ${album.isOwn ? html `
                    <!-- Only for registered user and creator of the album-->
                    <div class="actionBtn">
                        <a href="/edit/${album._id}" class="edit">Edit</a>
                        <a @click=${onDelete} href="#" class="remove">Delete</a>
                    </div>`: ''}
                </div>
            </div>

            

`};



export async function albumDetailsSet(ctx) {
    console.log('albumDetailsSet');

    const id = ctx.params.id;

    ctx.render(albumDetailsTemplate(getAlbumById(id,ctx)));
    ctx.updateNavigation('details');

}

async function getAlbumById(id,ctx) {
    const album = await data.getAlbumById(id);
    album.isOwn = album._ownerId === sessionStorage.getItem('userId') ? true : false;
    album.isLogged = sessionStorage.getItem('userId') != null ? true : false;

    async function onDelete(ev) {
        ev.preventDefault();
        console.log('Delete');
        const confirmed = confirm('Do you really want to delete this item?');

        if (!confirmed) {
            
            return;
        }
        const id = ctx.params.id;
        await data.deleteAlbumById(id);

        page.redirect('/catalog');
    }

    return albumDetailsItemTemplate(album, onDelete);
    
}