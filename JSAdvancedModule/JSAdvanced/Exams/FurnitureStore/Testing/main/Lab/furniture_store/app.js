window.addEventListener('load', solve);

function solve() {
    
    // model, year, description, price
    // add button
    const model = document.querySelector('#model');
    const year = document.querySelector('#year');
    const description = document.querySelector('#description');
    const price = document.querySelector('#price');

    const addButton = document.querySelector('#add');

    addButton.addEventListener('click', function(e) {

        e.preventDefault();
        const furnitureList = document.querySelector('#furniture-list');

        // validate input
        if (model.value.trim() === '' || year.value === ''
        || description.value.trim() === '' || price.value === '') {
            return;
        }

        if (Number(year.value) < 0 || Number(price.value) < 0) {
            return;
        }

        // create furniture list view
        const modelPriceRow = createElement('tr','info','');
        const furnitureModel = createElement('td','',model.value);
        const furniturePrice = createElement('td','',Number(price.value).toFixed(2));

        const furnitureActions = createElement('td','','');
        const moreBtn = createElement('button','moreBtn','More Info');
        const buyBtn = createElement('button','buyBtn','Buy it');
        furnitureActions.appendChild(moreBtn);
        furnitureActions.appendChild(buyBtn);

        modelPriceRow.appendChild(furnitureModel);
        modelPriceRow.appendChild(furniturePrice);
        modelPriceRow.appendChild(furnitureActions);

        const labels = Array.from(document.querySelectorAll('form label'));
        const yearDescriptionRow = createElement('tr','hide','');
        const yearCol = createElement('td','',labels[1].textContent+': '+year.value);
        const descriptionCol = createElement('td','',labels[2].textContent+': '+description.value);
        descriptionCol.colSpan = '3';

        yearDescriptionRow.appendChild(yearCol);
        yearDescriptionRow.appendChild(descriptionCol);

        furnitureList.appendChild(modelPriceRow);
        furnitureList.appendChild(yearDescriptionRow);

        moreBtn.addEventListener('click', function(e){

            if (e.target.textContent === 'More Info') {
                e.target.textContent = 'Less Info';
                yearDescriptionRow.style.display = 'contents';
            } else if (e.target.textContent === 'Less Info') {
                e.target.textContent = 'More Info';
                yearDescriptionRow.style.display = 'none';
            }
        });

        buyBtn.addEventListener('click', function(e) {

            // calculate the total price
            const totalPrice = document.querySelector('.total-price');
            const price = Number(furniturePrice.textContent);
            totalPrice.textContent = (Number(totalPrice.textContent) + price).toFixed(2);

            // remove furniture
            modelPriceRow.parentElement.removeChild(modelPriceRow);
            yearDescriptionRow.parentElement.removeChild(yearDescriptionRow);
        });

        // clear inputs
        model.value = '';
        year.value = '';
        description.value = '';
        price.value = '';
    });


    function createElement(type, clazz, value) {

        const element = document.createElement(type);

        if (clazz !== '') {
            element.className = clazz;
        }

        element.textContent = value;

        return element;
    }
}
