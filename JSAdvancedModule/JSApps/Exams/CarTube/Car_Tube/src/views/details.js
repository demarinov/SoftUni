import { html, until, data, getNavigation, getFooter, page} from '../lib.js';

const carDetailsTemplate = (carsPromise) => html`
${getNavigation()}
<section id="listing-details">
            <h1>Details</h1>
            ${until(carsPromise, html`<p> Loading &hellip`)}
        </section>
${getFooter()}         

`;

const carDetailsItemTemplate = (car, onDelete) => {
    const imgSrc = car.imageUrl.slice(0, 4) === 'http' ? car.imageUrl : `${car.imageUrl}`

    return html`
    <div class="details-info">
                <img src="${car.imageUrl}">
                <hr>
                <ul class="listing-props">
                    <li><span>Brand:</span>${car.brand}</li>
                    <li><span>Model:</span>${car.model}</li>
                    <li><span>Year:</span>${car.year}</li>
                    <li><span>Price:</span>${car.price}$</li>
                </ul>

                <p class="description-para">${car.description}</p>

                ${car.isOwn ? html `
                <div class="listings-buttons">
                    <a href="/edit/${car._id}" class="button-list">Edit</a>
                    <a @click=${onDelete} href="#" class="button-list">Delete</a>
                </div>` : ''}
    </div>
`};



export async function carDetailsSet(ctx) {
    console.log('carDetailsSet');

    const id = ctx.params.id;
    ctx.render(carDetailsTemplate(getCarById(id,ctx)));
    ctx.updateNavigation('details');

}

async function getCarById(id,ctx) {
    const car = await data.getCarById(id);
    car.isOwn = car._ownerId === sessionStorage.getItem('userId') ? true : false;

    async function onDelete(ev) {
        ev.preventDefault();
        const confirmed = confirm('Do you really want to delete this item?');

        if (!confirmed) {
            
            return;
        }
        const id = ctx.params.id;
        await data.deleteCarById(id);

        page.redirect('/listings');
    }

    return carDetailsItemTemplate(car, onDelete);
}