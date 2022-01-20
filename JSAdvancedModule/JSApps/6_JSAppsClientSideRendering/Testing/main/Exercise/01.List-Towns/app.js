import {html, render} from "../../../node_modules/lit-html/lit-html.js";

window.addEventListener('load', function(e) {
    // do some stuff

    const form = document.querySelector('body form');

    form.addEventListener('submit', (ev) => {doForm(ev);})
});

function doForm(ev) {
    ev.preventDefault();
    const formData = new FormData(ev.target);

    const inputArr = formData.get('towns').split(', ');

    if (inputArr.length > 0 && inputArr[0] !== '') {
        render(createTownTemplate(inputArr), document.querySelector('#root'));
    }

    // ev.target.reset();
}

const createTownTemplate = (towns) => {

    const template = html `
        <ul>
            ${towns.map(town => html `<li>${town}</li>`)}
        </ul>
    `;

    return template;
}