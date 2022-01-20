import {html, until, data} from '../src/lib.js';

const catalogDetailsTemplate = (cardsPromise) => html`
<div class="container">
<div class="row space-top">
    <div class="col-md-12">
        <h1>Furniture Details</h1>
    </div>
</div>
<div class="row space-top">
    ${until(cardsPromise, html `<p>Loading&hellip;</p>`)}
 </div>
 </div>   
`;

const catalogDetailsItemTemplate = (card) => {
const imgSrc = card.img.slice(0, 4) === 'http' ? card.img : `/${card.img}`
return html`
<div class="col-md-4">
                <div class="card text-white bg-primary">
                    <div class="card-body">
                        <img src="${imgSrc}" />
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <p>Make: <span>${card.make}</span></p>
                <p>Model: <span>${card.model}</span></p>
                <p>Year: <span>${card.year}</span></p>
                <p>Description: <span>${card.description}</span></p>
                <p>Price: <span>${card.price}</span></p>
                <p>Material: <span>${card.material}</span></p>
                ${card.isOwn ? html `
                <div>
                    <a href='/edit/${card._id}' class="btn btn-info">Edit</a>
                    <a href='/delete/${card._id}' class="btn btn-red">Delete</a>
                </div>`: ''}
            </div>    
`};



export async function catalogDetailsSet(ctx) {
    console.log('catalogDetailsSet');
    
    const id = ctx.params.id;
    ctx.render(catalogDetailsTemplate(getFurnitureById(id)));
    ctx.updateNavigation('details');

}

async function getFurnitureById(id) {
    const card = await data.getFurnitureById(id);
    card.isOwn = card._ownerId === sessionStorage.getItem('userId') ? true : false;
    return catalogDetailsItemTemplate(card);
}