
window.addEventListener('load', async () => {
    const main = document.querySelector('#main');

    const emailSpan = document.querySelector('.email span');
    const addBtn = document.querySelector('#addForm .add');

    if (sessionStorage.getItem('authToken') != null) {
        document.getElementById('user').style.display = 'inline-block';

        emailSpan.textContent = JSON.parse(sessionStorage.getItem('user'))['email'];
        const userLogout = document.querySelector('#user');

        userLogout.addEventListener('click', function (e) {

            logoutUser();
        });

        // hide login and register buttons
        document.getElementById('guest').style.display = 'none';

        // activate add button
        addBtn.disabled = false;

        addBtn.addEventListener('click', async function (e) {

            e.preventDefault();

            const angler = document.querySelector('input[name=angler]');
            const bait = document.querySelector('input[name=bait]');
            const weight = document.querySelector('input[name=weight]');
            const location = document.querySelector('input[name=location]');
            const captureTime = document.querySelector('input[name=captureTime]');
            const species = document.querySelector('input[name=species]');

            if (angler.value === '' || bait.value === ''
            || weight.value === '' || location.value === ''
            || captureTime.value === '' || species.value === '') {
                console.error('Input fields are empty!');
                return;
            }

            const c = {
                'angler':angler.value,
                "bait": bait.value,
                "weight":weight.value,
                "location":location.value,
                "captureTime":captureTime.value,
                "species":species.value
            };
            

            const data = await sendCreateCatchPostRequest(c);
            c['_ownerId'] = data['_ownerId'];
            c['_id'] = data['_id'];

            const catchesDivMain = document.querySelector('#catches');
            createCatch(c, catchesDivMain);

            // clear input
            angler.value = '';
            bait.value = '';
            weight.value = '';
            location.value = '';
            captureTime.value = '';
            species.value = '';
        });

    } else {
        document.getElementById('guest').style.display = 'inline-block';
        emailSpan.textContent = 'guest';
        document.getElementById('user').style.display = 'none';

        // deactivate add button
        addBtn.disabled = true;
    }

    // hide catches initially

    main.style.display = 'none';

    // create prompt span
    const spanClick = document.createElement('span');
    spanClick.textContent = 'Click to load catches';
    const mainList = Array.from(document.querySelector('main').children)

    const mainView = document.querySelector('main');
    mainView.innerHTML = '';

    mainList.forEach(e => {

        if (e.tagName === 'SECTION') {
            mainView.appendChild(spanClick);
        }
        mainView.appendChild(e)
    });

    // load the catches
    const loadBtn = document.querySelector('.load');

    loadBtn.addEventListener('click', loadCatches);
});

async function sendCreateCatchPostRequest(data) {

    const response = await fetch('http://localhost:3030/data/catches', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            'X-Authorization':sessionStorage.getItem('authToken')
        },
        body: JSON.stringify(data)
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}

async function loadCatches(e) {

    const mainSpan = document.querySelector('main span');
    if (mainSpan !== null) {
        mainSpan.parentElement.removeChild(mainSpan);
    }
    document.querySelector('#main').style.display = 'inline-block';

    // load catches from server...
    const catches = await sendGetCatchesRequest();

    // create catches structure...
    /* <div id="catches">
                    <div class="catch">
                            <label>Angler</label>
                            <input type="text" class="angler" value="Paulo Admorim">
                            <label>Weight</label>
                            <input type="text" class="weight" value="636">
                            <label>Species</label>
                            <input type="text" class="species" value="Atlantic Blue Marlin">
                            <label>Location</label>
                            <input type="text" class="location" value="Vitoria, Brazil">
                            <label>Bait</label>
                            <input type="text" class="bait" value="trolled pink">
                            <label>Capture Time</label>
                            <input type="number" class="captureTime" value="80">
                            <button class="update" data-id="07f260f4-466c-4607-9a33-f7273b24f1b4">Update</button>
                            <button class="delete" data-id="07f260f4-466c-4607-9a33-f7273b24f1b4">Delete</button>
                    </div> */
    const catchesDivMain = document.querySelector('#catches');
    // clear previous catches
    catchesDivMain.innerHTML = '';

    catches.forEach(c => {
        createCatch(c, catchesDivMain);
    });
}

function createCatch(c, catchesDivMain) {
    
    const catchDiv = createElement('div', 'catch', '');
        const labelAngler = createElement('label', '', 'Angler');
        const inputAngler = createElement('input', 'angler', c.angler);
        inputAngler.type = 'text';
        catchDiv.appendChild(labelAngler);
        catchDiv.appendChild(inputAngler);

        const labelWeight = createElement('label', '', 'Weight');
        const inputWeight = createElement('input', 'weight', c.weight);
        inputWeight.type = 'text';
        catchDiv.appendChild(labelWeight);
        catchDiv.appendChild(inputWeight);

        const labelSpecies = createElement('label', '', 'Species');
        const inputSpecies = createElement('input', 'species', c.species);
        inputSpecies.type = 'text';
        catchDiv.appendChild(labelSpecies);
        catchDiv.appendChild(inputSpecies);

        const labelLocation = createElement('label', '', 'Location');
        const inputLocation = createElement('input', 'location', c.location);
        inputLocation.type = 'text';
        catchDiv.appendChild(labelLocation);
        catchDiv.appendChild(inputLocation);

        const labelBait = createElement('label', '', 'Bait');
        const inputBait = createElement('input', 'bait', c.bait);
        inputBait.type = 'text';
        catchDiv.appendChild(labelBait);
        catchDiv.appendChild(inputBait);

        const labelCaptureTime = createElement('label', '', 'Capture Time');
        const inputCaptureTime = createElement('input', 'captureTime', c.captureTime);
        inputCaptureTime.type = 'text';
        catchDiv.appendChild(labelCaptureTime);
        catchDiv.appendChild(inputCaptureTime);

        const buttonUpd = createElement('button', 'update', 'Update');
        buttonUpd['data-id'] = c['_id'];
        const buttonDel = createElement('button', 'delete', 'Delete');
        buttonDel['data-id'] = c['_id'];

        buttonDel.addEventListener('click', function(e) {
            // only for the owner of the catch, check below
            sendDeleteCatchDeleteRequest(e.target['data-id']);
            catchDiv.parentElement.removeChild(catchDiv);
        });

        buttonUpd.addEventListener('click', function(e) {
            // get the new values

            const catchy = {
                'angler':inputAngler.value,
                "bait": inputBait.value,
                "weight":inputWeight.value,
                "location":inputLocation.value,
                "captureTime":inputCaptureTime.value,
                "species":inputSpecies.value
            };
            // only for the owner of the catch, check below
            sendUpdateCatchPutRequest(e.target['data-id'], catchy);
            
        });

        if (sessionStorage.getItem('authToken') === null) {
            buttonUpd.disabled = true;
            buttonDel.disabled = true;
            inputAngler.disabled = true;
            inputWeight.disabled = true;
            inputSpecies.disabled = true;
            inputLocation.disabled = true;
            inputBait.disabled = true;
            inputCaptureTime.disabled = true;
        } else {
            if (JSON.parse(sessionStorage.getItem('user'))['id'] !== c['_ownerId']) {
                buttonUpd.disabled = true;
                buttonDel.disabled = true;
                inputAngler.disabled = true;
                inputWeight.disabled = true;
                inputSpecies.disabled = true;
                inputLocation.disabled = true;
                inputBait.disabled = true;
                inputCaptureTime.disabled = true;
            } 
        }
        catchDiv.appendChild(buttonUpd);
        catchDiv.appendChild(buttonDel);

        catchesDivMain.appendChild(catchDiv);
}

async function sendUpdateCatchPutRequest(id, data) {

    const response = await fetch('http://localhost:3030/data/catches/'+id, {
        method: 'put',
        headers: {
            'Content-Type': 'application/json',
            'X-Authorization':sessionStorage.getItem('authToken')
        },
        body: JSON.stringify(data)
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}

async function sendDeleteCatchDeleteRequest(id) {

    const response = await fetch('http://localhost:3030/data/catches/'+id, {
        method: 'delete',
        headers: {
            'Content-Type': 'application/json',
            'X-Authorization':sessionStorage.getItem('authToken')
        }
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}

async function sendGetCatchesRequest() {

    const response = await fetch('http://localhost:3030/data/catches', {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}



async function logoutUser() {
    const token = sessionStorage.getItem('authToken');

    if (token != null) {
        sendLogoutRequest('http://localhost:3030/users/logout', token);
    }
}

async function sendLogoutRequest(url, token) {

    const result = await fetch(url, {
        method: 'get',
        headers: {
            'X-Authorization': token
        }

    });

    if (result.status >= 200 && result.status < 300) {
        let path = window.location.pathname;
        path = path.replace(/index.html/, 'index.html');
        sessionStorage.removeItem('authToken');
        sessionStorage.removeItem('user');
        window.location.pathname = path;

    } else {
        console.error(result.statusText);
    }

}

function createElement(type, clazz, value) {

    const element = document.createElement(type);

    if (clazz !== null) {
        element.className = clazz;
    }

    element.value = value;
    element.textContent = value;
    return element;
}
