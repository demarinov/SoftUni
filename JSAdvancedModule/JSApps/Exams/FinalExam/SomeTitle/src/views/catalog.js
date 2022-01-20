import { getAlbums } from '../api/data.js';
import {html, until, data, getFooter, getNavigation} from '../lib.js';

const albumsTemplate = (albumsPromise) => html`
${getNavigation()}
<section id="catalogPage">
            <h1>All Albums</h1>
            
            ${until(albumsPromise, html `<p>Loading albums &hellip;</p>`)}    
</section>                  
 ${getFooter()}                      
`;

export const albumItemTemplate = (album, logged) => html`
<div class="card-box">
                <img src="${album.imgUrl}">
                <div>
                    <div class="text-center">
                        <p class="name">Name: ${album.name}</p>
                        <p class="artist">Artist: ${album.artist}</p>
                        <p class="genre">Genre: ${album.genre}</p>
                        <p class="price">Price: $${album.price}</p>
                        <p class="date">Release Date: ${album.releaseDate}</p>
                    </div>
                    ${logged ? html `<div class="btn-group">
                    <a href="/details/${album._id}" id="details">Details</a>
                </div>` : ''}
                    
                </div>
            </div>
`;


export async function catalogSet(ctx) {
    console.log('catalogSet');

    ctx.render(albumsTemplate(getAllAlbums()));
    ctx.updateNavigation('game');
}

async function getAllAlbums() {
    const albums = await data.getAlbums();
    const logged = sessionStorage.getItem('userId') ? true : false;

    if (albums == null || albums.length === 0){

        return html `<p>No Albums in Catalog!</p>`;
    }

    return albums.map(a => albumItemTemplate(a, logged));
}
