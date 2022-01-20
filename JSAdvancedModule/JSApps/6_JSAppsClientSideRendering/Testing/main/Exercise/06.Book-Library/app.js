import {html, render} from '../../../node_modules/lit-html/lit-html.js';
import * as templates from "./templates.js";

async function request(url, options) {
    const response = await fetch(url, options);
    if (response.ok != true) {
        const error = await response.json();
        alert(error.message);
        throw new Error(error.message);
    }
    const data = await response.json();
    return data;
}

// function to load all books from server and display them
async function getAllBooks() {
    const books = await request('http://localhost:3030/jsonstore/collections/books');

    document.querySelector('tbody').innerHTML = '';
    const fragment = document.createDocumentFragment();

    render(templates.createRowsTemplate(Object.entries(books)), fragment);
    document.querySelector('tbody').appendChild(fragment);
}

// function for creating a new book
async function createBook(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const book = {
        title: formData.get('title'),
        author: formData.get('author')
    };

    if (book.title == '' || book.author == '') {
        return alert('All fields are required!');
    }

    await request('http://localhost:3030/jsonstore/collections/books', {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
    });

    event.target.reset();
}

// function for updating an existing book using ID
async function updateBook(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const id = formData.get('id');
    const book = {
        title: formData.get('title'),
        author: formData.get('author')
    };

    if (book.title == '' || book.author == '') {
        return alert('All fields are required!');
    }

    await request('http://localhost:3030/jsonstore/collections/books/' + id, {
        method: 'put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
    });

    document.getElementById('add-form').style.display = 'block';
    document.getElementById('edit-form').style.display = 'none';
    event.target.reset();
}

// function for deleting an existing book using ID
async function deleteBook(id) {
    await request('http://localhost:3030/jsonstore/collections/books/' + id, {
        method: 'delete',
    });
}

// detect user clicks on Edit/Delete buttons
function handleTableClick(event) {
    if (event.target.className == 'editBtn') {
        document.getElementById('add-form').style.display = 'none';
        document.getElementById('edit-form').style.display = 'block';
        const bookId = event.target.parentNode.parentNode.dataset.id;
        loadBookForEditting(bookId);
    } else if (event.target.className == 'deleteBtn') {
        const confirmed = confirm('Are you sure you want to delete this book?');
        if (confirmed) {
            const bookId = event.target.parentNode.parentNode.dataset.id;
            deleteBook(bookId);
        }
    }
}

async function loadBookForEditting(id) {
    const book = await request('http://localhost:3030/jsonstore/collections/books/' + id);

    document.querySelector('#edit-form [name="id"]').value = id;
    document.querySelector('#edit-form [name="title"]').value = book.title;
    document.querySelector('#edit-form [name="author"]').value = book.author;
}


function start() {
    document.getElementById('loadBooks').addEventListener('click', getAllBooks);
    document.getElementById('add-form').addEventListener('submit', createBook);
    document.getElementById('edit-form').addEventListener('submit', updateBook);
    document.querySelector('table').addEventListener('click', handleTableClick);
}

function createDomStructureInit() {
    const fragment = document.createDocumentFragment();
    // render composite

    render(templates.composeBaseStructure(), fragment);
    document.querySelector('body').appendChild(fragment);

    start();
}



createDomStructureInit();