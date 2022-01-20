import { html, data, validateFurniture } from '../src/lib.js';


const editTemplate = (onSubmit) => html`
<div class="container">
<div class="row space-top">
    <div class="col-md-12">
        <h1>Edit Furniture</h1>
        <p>Please fill all fields.</p>
    </div>
</div>
<form @submit=${onSubmit}>
    <div class="row space-top">
        <div class="col-md-4">
            <div class="form-group">
                <label class="form-control-label" for="new-make">Make</label>
                <input class="form-control" id="new-make" type="text" name="make" value="Table">
            </div>
            <div class="form-group has-success">
                <label class="form-control-label" for="new-model">Model</label>
                <input class="form-control" id="new-model" type="text" name="model" value="Swedish">
            </div>
            <div class="form-group has-danger">
                <label class="form-control-label" for="new-year">Year</label>
                <input class="form-control" id="new-year" type="number" name="year" value="2015">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="new-description">Description</label>
                <input class="form-control" id="new-description" type="text" name="description" value="Medium table">
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <label class="form-control-label" for="new-price">Price</label>
                <input class="form-control" id="new-price" type="number" name="price" value="235">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="new-image">Image</label>
                <input class="form-control" id="new-image" type="text" name="img" value="/images/table.png">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="new-material">Material (optional)</label>
                <input class="form-control" id="new-material" type="text" name="material" value="Wood">
            </div>
            <input type="submit" class="btn btn-info" value="Edit" />
        </div>
    </div>
</form>
</div>
`;

export async function editSet(ctx) {
    console.log('editSet');

    ctx.render(editTemplate(onSubmit));
    ctx.updateNavigation();

    const id = ctx.params.id;
    prefillForm();

    async function prefillForm() {

        const furniture = await data.getFurnitureById(id);

        const make = document.querySelector('#new-make');
        const model = document.querySelector('#new-model');
        const year = document.querySelector('#new-year');
        const description = document.querySelector('#new-description');
        const price = document.querySelector('#new-price');
        const img = document.querySelector('#new-image');
        const material = document.querySelector('#new-material');

        make.value = furniture.make;
        model.value = furniture.model;
        year.value = furniture.year;
        description.value = furniture.description;
        price.value = furniture.price;
        img.value = furniture.img;
        material.value = furniture.material;

    }

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit edit form');

        const formData = new FormData(document.querySelector('.container form'));

        const make = formData.get('make');
        const model = formData.get('model');
        const year = formData.get('year');
        const description = formData.get('description');
        const price = formData.get('price');
        const img = formData.get('img');
        const material = formData.get('material');

        // apply validation rules

        let { makeValid, modelValid, yearValid, descValid, priceValid, imgValid }
            = validateFurniture(Object.fromEntries(formData), ctx);


        if (!makeValid
            || !modelValid
            || !yearValid
            || !descValid
            || !priceValid
            || !imgValid) {
            console.error('Invalid fields!');
            return;
        }

        const furniture = Object.fromEntries(formData);

        await data.editFurniture(id, furniture);

        ev.target.reset();
        ctx.page.redirect('/catalog');
    }
}