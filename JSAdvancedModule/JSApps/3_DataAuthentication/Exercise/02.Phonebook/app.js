function attachEvents() {
    
    const load = document.querySelector('#btnLoad');

    load.addEventListener('click', sendGetRequest);

    const create = document.querySelector('#btnCreate');

    create.addEventListener('click', sendPostRequest);

    const person = document.querySelector('#person');
    const phone = document.querySelector('#phone');


    async function sendPostRequest() {

        const data = {
            "person":person.value,
            "phone":phone.value
        };

        const response = await fetch('http://localhost:3030/jsonstore/phonebook',
        {
            method: 'post',
            headers: {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        });

        if (response.status === 200) {

            const pureResponse = await response.json();

            // clear data
            person.value = '';
            phone.value = '';

            load.click();
        } else {
            console.error("error");
        }
    }

    async function sendGetRequest() {

        const response = await fetch('http://localhost:3030/jsonstore/phonebook',
        {
            method: 'get',
            headers: {
                "Content-Type":"application/json"
            }
        });

        if (response.status === 200) {

            const pureResponse = await response.json();

            loadPhoneBook(Object.values(pureResponse));
        } else {
            console.error("error");
        }
    }

    async function sendDeleteRequest(id) {
        console.log(id);
        const response = await fetch('http://localhost:3030/jsonstore/phonebook/'+id,
        {
            method: 'delete',
            headers: {
                "Content-Type":"application/json"
            }
        });

        if (response.status === 200) {

            const pureResponse = await response.json();
            
        } else {
            console.error("error");
        }
    }

    function loadPhoneBook(phoneBookList) {

        const ul = document.querySelector('#phonebook');

        // clear list
        ul.innerHTML = '';

        for(const phonebook of phoneBookList) {

            const li = createElement('li','',phonebook.person + ': '+phonebook.phone);
            ul.appendChild(li);
            const deleteBtn = createElement('button','','Delete');
            li.appendChild(deleteBtn);

            deleteBtn.addEventListener('click', function(e) {
                sendDeleteRequest(phonebook['_id']);
                ul.removeChild(li);
            });
        }


    }

    function createElement(type, clazz, value) {

        const element = document.createElement(type);

        if (clazz !== undefined) {

            element.className = clazz;
        }

        element.textContent = value;

        return element;
    }
}

attachEvents();