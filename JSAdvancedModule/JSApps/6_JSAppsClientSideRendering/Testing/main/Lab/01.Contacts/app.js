import {contacts} from "./contacts.js";

import { html, render } 
	from '../../../node_modules/lit-html/lit-html.js';


window.addEventListener('load', function(e){
    renderContacts();
});

function renderContacts() {

    const contactsTemplate = (data) => {
        return html `${data.map(c => {
            console.log(c);
        
            return html `<div class="contact card">
            <div>
                <i class="far fa-user-circle gravatar"></i>
            </div>
            <div class="info">
                <h2>Name: ${c.name}</h2>
                <button class="detailsBtn" @click=${showDetails.bind(this,c.id)}>Details</button>
                <div class="details" id="card${c.id}">
                    <p>Phone number: ${c.phoneNumber}</p>
                    <p>Email: ${c.email}</p>
                </div>
            </div>
        </div>`;
        })}`;
    };

    render(contactsTemplate(contacts), document.querySelector('#contacts'));
}

function showDetails(id) {

    const detailCard = document.querySelector("#card"+id);

    if (detailCard['style']['display'] === 'none' 
    || detailCard['style']['display'] === '') {
        detailCard['style']['display'] = 'block';
    } else {
        detailCard['style']['display'] = 'none';
    }
}
