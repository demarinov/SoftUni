import { data, until,getNavigation, getFooter, html} from "../lib.js";


const profileTemplate = (carsPromise) => html `
${getNavigation()}
<section id="my-listings">
        <h1>My car listings</h1>
        <div class="listings">

                <!-- Display all records -->
    
            ${until(carsPromise, html `Loading memes &hellip`)} 
    
        </div>
</section>
${getFooter()}
`;

const carTemplate = (car) => {
    return html `
    
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
}

export async function profileSet(ctx) {
    console.log('profileSet');

    const userId = sessionStorage.getItem('userId');

    ctx.render(profileTemplate(getUserCars(userId)));
    ctx.updateNavigation('profile');

}

async function getUserCars(userId) {

    let userCars = await data.getCarsByUserId(userId);
    if (userCars.length === 0) {

        return html `<p class="no-cars"> You haven't listed any cars yet.</p>`;
    }
    
    return userCars.map(carTemplate);
}