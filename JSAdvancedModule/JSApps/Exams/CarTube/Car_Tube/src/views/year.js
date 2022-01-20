import { data, html, getNavigation, getFooter, until } from "../lib.js";
import { carItemTemplate } from "./listings.js";

const carsTemplate = (onSearch) => {
   
    return html `
    ${getNavigation()}
    <section id="search-cars">
    <h1>Filter by year</h1>

    <div class="container">
        <input id="search-input" type="text" name="search" placeholder="Enter desired production year">
        <button @click=${onSearch} class="button-list">Search</button>
    </div>

    <h2>Results:</h2>
    <div class="listings">
        
    </div>
</section>
${getFooter()}
    `;
};

const carsListings = (carsPromise) => {
    return html `
    <!-- Display all records -->
    ${until(carsPromise, html `Loading cars&hellip`)}
    `;
}


export function yearSet(ctx) {

    ctx.render(carsTemplate(onSearch));
    ctx.updateNavigation('year');

    function onSearch(ev) {
        ev.preventDefault();

        const searchField = document.querySelector('#search-input');

        const year = searchField.value;
        ctx.renderInContainer(carsListings(getCarsByYear(year)), 
        document.querySelector('#search-cars .listings'));

        //reset
        searchField.value = '';
    }
} 

async function getCarsByYear(year){

    const cars = await data.getCarsByYear(year);

    if (cars.length === 0) {
        // or No matching listings
        return html `<p class="no-cars"> No results.</p>`;
    }

    return cars.map(carItemTemplate);
}