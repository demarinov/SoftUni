import { html, render} from '../node_modules/lit-html/lit-html.js';
import * as data from './api/data.js';
import {until} from '../node_modules/lit-html/directives/until.js';
import page from "../../node_modules/page/page.mjs";


function validateFurniture(furnitureIn, ctx) {

    const makeValid = ctx.validationRules['make'](furnitureIn.make);
    const modelValid = ctx.validationRules['model'](furnitureIn.model);
    const yearValid = ctx.validationRules['year'](furnitureIn.year);
    const descValid = ctx.validationRules['description'](furnitureIn.description);
    const priceValid = ctx.validationRules['price'](furnitureIn.price);
    const imgValid = ctx.validationRules['img'](furnitureIn.img);

    const makeField = document.querySelector('#new-make');
    const modelField = document.querySelector('#new-model');
    const yearField = document.querySelector('#new-year');
    const descField = document.querySelector('#new-description');
    const priceField = document.querySelector('#new-price');
    const imgField = document.querySelector('#new-image');

    makeField.classList.remove('is-invalid');
    makeField.classList.remove('is-valid');

    modelField.classList.remove('is-invalid');
    modelField.classList.remove('is-valid');

    yearField.classList.remove('is-invalid');
    yearField.classList.remove('is-valid');

    descField.classList.remove('is-invalid');
    descField.classList.remove('is-valid');

    priceField.classList.remove('is-invalid');
    priceField.classList.remove('is-valid');

    imgField.classList.remove('is-invalid');
    imgField.classList.remove('is-valid');

    !makeValid ? makeField.className += ' is-invalid' : makeField.className += ' is-valid';
    !modelValid ? modelField.className += ' is-invalid' : modelField.className += ' is-valid';
    !yearValid ? yearField.className += ' is-invalid' : yearField.className += ' is-valid';
    !descValid ? descField.className += ' is-invalid' : descField.className += ' is-valid';
    !priceValid ? priceField.className += ' is-invalid' : priceField.className += ' is-valid';
    !imgValid ? imgField.className += ' is-invalid' : imgField.className += ' is-valid';


    let valididationFieldResults = {
        makeValid, modelValid, yearValid, descValid,
        priceValid, imgValid
    };
    return valididationFieldResults;
}

export {html, data, until, page, render, validateFurniture};