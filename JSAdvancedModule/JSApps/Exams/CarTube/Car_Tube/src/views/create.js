import { html, data, getNotification,getNavigation, 
    getFooter, triggerError} from '../lib.js';

const createTemplate = (onSubmit) => {
    return html
        ` 
        ${getNavigation()}
        <section id="create-listing">
            <div class="container">
                <form id="create-form" @submit=${onSubmit}>
                    <h1>Create Car Listing</h1>
                    <p>Please fill in this form to create an listing.</p>
                    <hr>

                    <p>Car Brand</p>
                    <input type="text" placeholder="Enter Car Brand" name="brand">

                    <p>Car Model</p>
                    <input type="text" placeholder="Enter Car Model" name="model">

                    <p>Description</p>
                    <input type="text" placeholder="Enter Description" name="description">

                    <p>Car Year</p>
                    <input type="number" placeholder="Enter Car Year" name="year">

                    <p>Car Image</p>
                    <input type="text" placeholder="Enter Car Image" name="imageUrl">

                    <p>Car Price</p>
                    <input type="number" placeholder="Enter Car Price" name="price">

                    <hr>
                    <input type="submit" class="registerbtn" value="Create Listing">
                </form>
            </div>
        </section>
        ${getFooter()}
`};

export async function createSet(ctx) {
    console.log('createSet');

    ctx.render(createTemplate(onSubmit));
    ctx.updateNavigation('create');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit create form');

        const formData = new FormData(document.querySelector('#create-form'));

        const brand = formData.get('brand');
        const model = formData.get('model');
        const price = formData.get('price');
        const year = formData.get('year');
        
        const imageUrl = formData.get('imageUrl');
        const description = formData.get('description');

        // apply validation rules

        if (brand === '' || model === '' || price === '' || price < 0
            || imageUrl === '' || year === '' || year < 0
            || description === '') {
            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            // triggerError('Invalid or empty fields!',null);
            return;
        }

        const car = Object.fromEntries([...formData.entries()]);
        car['year'] = Number(car['year']);
        car['price'] = Number(car['price']);

        let error = false;
        let msg = '';
        await data.createCar(car).catch(e => {error = true; msg = e.message;});

        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/listings');
        
    }
}

