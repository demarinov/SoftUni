import {html, until, data} from '../src/lib.js';

const catalogTemplate = (cardsPromise, isPub) => html`
<div class="container">
<div class="row space-top">
    <div class="col-md-12">
        ${!isPub ? html `
        <h1>Welcome to Furniture System</h1>
        <p>Select furniture from the catalog to view details.</p>`
        : html `<h1>My Furniture</h1>
        <p>This is a list of your publications.</p> `
        }
    </div>
</div>
<div class="row space-top">
    ${until(cardsPromise, html `<p>Loading&hellip;</p>`)}
 </div>
 </div>   
`;

const catalogItemTemplate = (card) => html`
<div class="col-md-4">
    <div class="card text-white bg-primary">
        <div class="card-body">
            <img src="${card.img}" />
            <p>${card.description}</p>
            <footer>
                <p>Price: <span>${card.price} $</span></p>
            </footer>
            <div>
                <a href='/details/${card._id}' class="btn btn-info">Details</a>
            </div>
        </div>
    </div>
 </div>       
`;



export async function catalogSet(ctx) {
    console.log('catalogSet');

    ctx.render(catalogTemplate(getAllFurnitures(), false));
    ctx.updateNavigation('catalog');
}

export async function catalogPublicationsSet(ctx) {
    console.log('catalogPublicationsSet');

    if (sessionStorage.getItem('userToken') != null) {
        const userId = sessionStorage.getItem('userId');
        ctx.render(catalogTemplate(getFurnituresByUserId(userId),true));

    }

    ctx.updateNavigation('publications');
}

async function getAllFurnitures() {
    const cards = await data.getFurnitures();
    return cards.map(catalogItemTemplate);
}

async function getFurnituresByUserId(userId) {
    const cards = await data.getFurnituresByUserId(userId);
    
    return cards.map(catalogItemTemplate);
}