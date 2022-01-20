import { data, getNavigation, html, until, getFooter} from "../lib.js";

const searchTemplate = (onSearch) => html `
${getNavigation()}
<section id="searchPage">
<h1>Search by Name</h1>

<div class="search">
    <input id="search-input" type="text" name="search" placeholder="Enter desired albums's name">
    <button @click=${onSearch} class="button-list">Search</button>
</div>

<h2>Results:</h2>

<!--Show after click Search button-->
<div class="search-result">

</div>
</section>
${getFooter()}
`;

const searchItem = (searchPromise) => html `
    ${until(searchPromise, html `Loading &hellip;`)}
`;

const searchItemTemplate = (album, logged) => html `

<!--If have matches-->
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

export async function searchSet(ctx) {

    ctx.render(searchTemplate(onSearch));
    ctx.updateNavigation('search');

    async function onSearch(ev) {

        ev.preventDefault();
        const query = document.querySelector('#search-input').value;


        if (query === '') {

            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            return;
        }

        

        ctx.renderInContainer(searchItem(getSearchAlbums(query)), 
        document.querySelector('.search-result'));

    }
}

async function getSearchAlbums(query) {

    const albums = await data.getSearchAlbums(query);
    const logged = sessionStorage.getItem('userId') ? true : false;

    console.log(albums);
    if (albums == null || albums.length === 0) {

        return html `<p class="no-result">No result.</p>`;
    }

    return albums.map(a => searchItemTemplate(a, logged));
}