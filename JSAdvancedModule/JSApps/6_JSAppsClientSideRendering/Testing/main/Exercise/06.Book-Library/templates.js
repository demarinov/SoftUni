import {html, render} from '../../../node_modules/lit-html/lit-html.js';

const composeBaseStructure = () => {
    
    return html `
        ${createLoadBooksBtnTemplate()}
        ${createTable()}
        ${createAddFormContainer()}
        ${createEditFormContainer()}
        
    `;
}

const createRowsTemplate = (books) => {

    return html `
        ${books.map(createRowTemplate)}
    `;
}

const createRowTemplate = ([id,book]) => {
    return html `
        <tr data-id="${id}">
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>
                <button class="editBtn">Edit</button>
                <button class="deleteBtn">Delete</button>
            </td>
        </tr>`;
}

const createTable = () => {

    return html `
    <table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    </tbody>
    </table>
    `;
}

const createEditFormContainer = () => {

    return html `
        <form id="edit-form" style="display:none">
        <input type="hidden" name="id">
        <h3>Edit book</h3>
        <label>TITLE</label>
        <input type="text" name="title" placeholder="Title...">
        <label>AUTHOR</label>
        <input type="text" name="author" placeholder="Author...">
        <input type="submit" value="Save">
        </form>
    `;
}

const createAddFormContainer = () => {

    return html `
        <form id="add-form">
        <h3>Add book</h3>
        <label>TITLE</label>
        <input type="text" name="title" placeholder="Title...">
        <label>AUTHOR</label>
        <input type="text" name="author" placeholder="Author...">
        <input type="submit" value="Submit">
        </form>
    `;
}

const createLoadBooksBtnTemplate = () => {

    return html `
        <button id="loadBooks">LOAD ALL BOOKS</button>
    `;
}

export {composeBaseStructure,createRowsTemplate};