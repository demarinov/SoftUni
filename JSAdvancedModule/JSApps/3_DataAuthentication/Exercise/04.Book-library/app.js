

let books = [];

window.addEventListener('load', initBooks);

const loadBooksBtn = document.querySelector('#loadBooks');

const formBtn = document.querySelector('form button');

formBtn.addEventListener('click', createOrUpdateBook);

loadBooksBtn.addEventListener('click', loadBooks);

let editedId = '';
let editedRow = null;

function initBooks(e) {

    const tableBtns = Array.from(document.querySelectorAll('tr td button'));

    tableBtns.forEach(tb => {
        if (tb.textContent === 'Delete') {

            tb.addEventListener('click', function (e) {
                deleteBook(e.target.parentElement.parentElement, '');
            });
        } else if (tb.textContent === 'Edit') {
            tb.addEventListener('click', function (e) {
                formBtn.textContent = 'Save';

                const formTags = Array.from(formBtn.parentElement.children);

                const header = formTags[0];
                header.textContent = 'Edit Form';
                editedId = '';
                editedRow = tb.parentElement.parentElement;
            });
        }
    });

}

async function createOrUpdateBook(e) {

    e.preventDefault();
    const title = document.querySelector('input[name=title]');
    const author = document.querySelector('input[name=author]');

    if (title.value === '' || author.value === '') {
        console.error('Invalid input: empty!');
        return;
    }

    const data = {
        "author": author.value,
        "title": title.value

    };

    if (e.target.textContent === 'Submit') {
        await sendBookPostRequest(data);
    } else if (e.target.textContent === 'Save') {
        // send put request        
        if (editedId !== '') {
            await sendBookPutRequest(editedId, data);
        }

        // reset submit controls
        e.target.textContent = 'Submit';

        const formTags = Array.from(formBtn.parentElement.children);

        const header = formTags[0];
        header.textContent = 'Form';

        const cols = Array.from(editedRow.children);

        cols[0].textContent = title.value; 
        cols[1].textContent = author.value; 
    }

    // clear input
    author.value = '';
    title.value = '';

}

async function loadBooks(e) {
    const tableBooks = document.querySelector('body table tbody');

    await sendBooksGetRequest();

    const currentBookRows = Array.from(tableBooks.children);

    // console.log(tableBooks);

    if (books.length > 0) {

        // remove old books
        books.forEach(b => {
            currentBookRows.forEach(br => {

                const cols = Array.from(br.children);

                if (cols[1].textContent === b.author
                    && cols[0].textContent === b.title) {
                    br.parentElement.removeChild(br);
                }
            });
        });

        books.forEach(b => {

            const tr = createElement('tr', '', '');

            const titleCol = createElement('td', '', b.title);
            const authorCol = createElement('td', '', b.author);

            const editBtn = createElement('button', '', 'Edit');
            const deleteBtn = createElement('button', '', 'Delete');
            const buttonCol = createElement('td', '', '');

            buttonCol.appendChild(editBtn);
            buttonCol.appendChild(deleteBtn);

            const bookId = b['_id'];

            deleteBtn.addEventListener('click', function (e) {

                const deleteRow = e.target.parentElement.parentElement;
                deleteBook(deleteRow, bookId);
            });

            editBtn.addEventListener('click', function (e) {
                formBtn.textContent = 'Save';
                const formTags = Array.from(formBtn.parentElement.children);

                const header = formTags[0];
                header.textContent = 'Edit Form';
                editedId = bookId;
                editedRow = e.target.parentElement.parentElement;
            });

            tr.appendChild(titleCol);
            tr.appendChild(authorCol);
            tr.appendChild(buttonCol);

            tableBooks.appendChild(tr);
        })

    }
}

async function deleteBook(deleteRow, id) {

    if (id !== '') {
        await sendBookDeleteRequest(id);
    }

    books.forEach(b => {
        if (b._id === id) {
            delete b;
        }
    });

    deleteRow.parentElement.removeChild(deleteRow);

}

async function sendBookDeleteRequest(id) {
    const response =
        await fetch('http://localhost:3030/jsonstore/collections/books/' + id,
            {
                method: 'delete',
                headers: {
                    "Content-Type": "application/json"
                }
            });

    if (response.status === 200) {

        const pureResponse = await response.json();

        // console.log(Object.values(pureResponse));

        // books = Object.values(pureResponse);
    } else {
        console.error(response.statusText);
    }

}

async function sendBookPutRequest(id,data) {
    const response =
        await fetch('http://localhost:3030/jsonstore/collections/books/'+id,
            {
                method: 'put',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

    if (response.status === 200) {

        const pureResponse = await response.json();

        // console.log(Object.values(pureResponse));

        const newBook = Object.values(pureResponse)[0];
        // update books cache
        books.forEach(b => {
            if (b._id === id) {
                b.title = data.title; 
                b.author = data.author; 
            }
        });

    } else {
        console.error(response.statusText);
    }


}

async function sendBookPostRequest(data) {
    const response =
        await fetch('http://localhost:3030/jsonstore/collections/books',
            {
                method: 'post',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

    if (response.status === 200) {

        const pureResponse = await response.json();

        // console.log(Object.values(pureResponse));

        books.push(Object.values(pureResponse));
    } else {
        console.error(response.statusText);
    }


}

async function sendBooksGetRequest() {
    const response =
        await fetch('http://localhost:3030/jsonstore/collections/books',
            {
                method: 'get',
                headers: {
                    "Content-Type": "application/json"
                }
            });

    if (response.status === 200) {

        const pureResponse = await response.json();

        // console.log(Object.values(pureResponse));

        books = Object.values(pureResponse);
    } else {
        console.error(response.statusText);
    }


}


function createElement(tag, clazz, value) {

    const element = document.createElement(tag);

    if (clazz !== '') {
        element.className = clazz;
    }

    element.value = value;
    element.textContent = value;

    return element;
}