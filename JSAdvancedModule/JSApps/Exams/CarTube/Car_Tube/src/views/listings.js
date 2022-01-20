import {html, until, data, getFooter, getNavigation} from '../lib.js';

const carsTemplate = (carsPromise) => html`
${getNavigation()}
<section id="car-listings">
            <h1>Car Listings</h1>
            <div class="listings">

                <!-- Display all records -->
                 ${until(carsPromise, html `Loading cars&hellip`)} 
             </div>
  </section>              
${getFooter()}
`;

export const carItemTemplate = (car) => html`
<div class="listing">
<div class="preview">
    <img src="${car.imageUrl}">
</div>
<h2>${car.brand} ${car.model}</h2>
<div class="info">
    <div class="data-info">
        <h3>Year: ${car.year}</h3>
        <h3>Price: ${car.price} $</h3>
    </div>
    <div class="data-buttons">
        <a href="/details/${car._id}" class="button-carDetails">Details</a>
    </div>
</div>
</div> 
`;



export async function listingSet(ctx) {
    console.log('listingSet');

    ctx.render(carsTemplate(getAllListings()));
    ctx.updateNavigation('listing');
}

// export async function catalogPublicationsSet(ctx) {
//     console.log('catalogPublicationsSet');

//     if (sessionStorage.getItem('userToken') != null) {
//         const userId = sessionStorage.getItem('userId');
//         ctx.render(catalogTemplate(getFurnituresByUserId(userId),true));

//     }

//     ctx.updateNavigation('publications');
// }

async function getAllListings() {
    const cars = await data.getCars();

    if (cars == null || cars.length === 0){

        return html `<h2>No cars in database.</h2>`;
    }

    return cars.map(carItemTemplate);
}
