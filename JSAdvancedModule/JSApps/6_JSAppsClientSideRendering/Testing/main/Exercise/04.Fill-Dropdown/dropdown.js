
import {html, render} from '../../../node_modules/lit-html/lit-html.js';

window.addEventListener('load',async function(e) {

    const items = Object.values(await getAllItems());
    renderOptions(items);
    const form = document.querySelector('form');

    form.addEventListener('submit', (ev) => {

        ev.preventDefault();
        addItem();
    })
});

function renderOptions(items) {

    const selectElement = document.querySelector('#menu');
    const currentItems = Array.from(selectElement.querySelectorAll('option'));
    if (currentItems.length > 0) {
        
        currentItems.forEach(item => items.push(item));
    }
    render(createOptionsTemplate(items),selectElement);
}

function addItem() {

    const textElement = document.querySelector('#itemText');

    const data = {
        text: textElement.value
    }

    postItem(data);

    textElement.value='';
}

const createOptionsTemplate = (items) => {
    return html `${items.map((item) => 
        html `<option value=${item['_id']}>${item.text}</option>`)}
    `;
}

async function postItem(data) {

    const response = await fetch('http://localhost:3030/jsonstore/advanced/dropdown',{

        method: 'post',
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify(data)
    });

    if (response.status === 200) {

        const responseJSON = await response.json();

        // render and update list of options
        renderOptions([responseJSON]);
        return responseJSON;
    }

    return null;

}

async function getAllItems() {

    const response = await fetch('http://localhost:3030/jsonstore/advanced/dropdown',{

        method: 'get',
        headers: {
            "Content-Type":"application/json"
        }
    });

    if (response.status === 200) {

        const data = await response.json();
        return data;
    }

    return [];
}