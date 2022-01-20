import { html, data, triggerError, getNotification,getNavigation, 
    getFooter} from '../lib.js';


const editTemplate = (onSubmit) => html`
${getNotification()}
${getNavigation()}
<section id="edit-listing">
            <div class="container">

                <form id="edit-form" @submit=${onSubmit}>
                    <h1>Edit Car Listing</h1>
                    <p>Please fill in this form to edit an listing.</p>
                    <hr>

                    <p>Car Brand</p>
                    <input type="text" placeholder="Enter Car Brand" name="brand" value="">

                    <p>Car Model</p>
                    <input type="text" placeholder="Enter Car Model" name="model" value="">

                    <p>Description</p>
                    <input type="text" placeholder="Enter Description" name="description" value="">

                    <p>Car Year</p>
                    <input type="number" placeholder="Enter Car Year" name="year" value="">

                    <p>Car Image</p>
                    <input type="text" placeholder="Enter Car Image" name="imageUrl" value="">

                    <p>Car Price</p>
                    <input type="number" placeholder="Enter Car Price" name="price" value="">

                    <hr>
                    <input type="submit" class="registerbtn" value="Edit Listing">
                </form>
            </div>
        </section>
        ${getFooter()}
    `;

export async function editSet(ctx) {
    console.log('editSet');

    ctx.render(editTemplate(onSubmit));
    ctx.updateNavigation();

    const id = ctx.params.id;
    prefillForm();

    async function prefillForm() {

        const car = await data.getCarById(id);

        const brand = document.querySelector('input[name="brand"]');
        const model = document.querySelector('input[name="model"]');
        const year = document.querySelector('input[name="year"]');
        const price = document.querySelector('input[name="price"]');
        const imageUrl = document.querySelector('input[name="imageUrl"]');
        const description = document.querySelector('input[name="description"]');

        brand.value = car.brand;
        model.value = car.model;
        year.value = car.year;
        price.value = car.price;
        imageUrl.value = car.imageUrl;
        description.value = car.description;

    }

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit edit form');

        const formData = new FormData(document.querySelector('#edit-form'));

        const brand = formData.get('brand');
        const model = formData.get('model');
        const year = formData.get('year');
        const price = formData.get('price');
        const imageUrl = formData.get('imageUrl');
        const description = formData.get('description');

        // apply validation rules


        if (brand === '' || model === ''
            || imageUrl === '' || year === '' || year < 0
            || description === '' || price === '' || price < 0) {
            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            // triggerError('Invalid or empty fields!', null);
            return;
        }
          
        const car = Object.fromEntries(formData);
        car['year'] = Number(car['year']);
        car['price'] = Number(car['price']);

        let error = false;
        let msg = '';
        await data.editCar(id, car).catch(e => {error = true; msg=e.message;});

        console.log(error);
        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/details/'+id);
    }
}