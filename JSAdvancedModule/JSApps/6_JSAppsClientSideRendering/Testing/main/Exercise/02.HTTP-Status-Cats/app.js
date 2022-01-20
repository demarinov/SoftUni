

import {cats} from './catSeeder.js';
import {html, render} from '../../../node_modules/lit-html/lit-html.js';

window.addEventListener('load', function(e) {
    console.log("app loaded");
    console.log(cats);

    render(catsTemplate(cats), document.querySelector('#allCats'));

});

const catsTemplate = (cats) => {

    return html `<ul>
        ${ cats.map( cat => html `
        <li>
        <img src="./images/${cat.imageLocation}.jpg" width="250" height="250" alt="Card image cap">
        <div class="info">
            <button class="showBtn" @click=${showStatusCode}>Show status code</button>
            <div class="status" style="display: none" id="${cat.id}">
                <h4>Status Code: ${cat.statusCode}</h4>
                <p>${cat.statusMessage}</p>
            </div>
        </div>
        </li>`
        )};
    </ul>
    `;
}

function showStatusCode(ev) {
    ev.preventDefault();

    const divStatus = ev.target.parentElement.querySelector('.status');
    if (ev.target.textContent === 'Show status code') {
        divStatus.style.display = 'block';
        ev.target.textContent = 'Hide status code';
    } else if (ev.target.textContent === 'Hide status code') {
        divStatus.style.display = 'none';
        ev.target.textContent = 'Show status code';
    }

}