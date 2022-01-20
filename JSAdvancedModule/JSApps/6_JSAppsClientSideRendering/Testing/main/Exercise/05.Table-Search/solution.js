
import { html, render } from '../../../node_modules/lit-html/lit-html.js';

function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   // load all info
   loadAllPeople();

   function clearSelectedPeople() {
      const tableRowsWithPeople = Array.from(document.querySelectorAll('.container tbody tr'));
      tableRowsWithPeople.forEach(personRow => {

         if (personRow.className === 'select') {
            // match, change class
            personRow.classList.remove('select');
         }
      })
   }

   function onClick() {
      // clear previous search
      clearSelectedPeople();

      // search for text
      const searchText = document.querySelector('#searchField');

      const rg = new RegExp(searchText.value, 'i');
      const tableRowsWithPeople = Array.from(document.querySelectorAll('.container tbody tr'));

      tableRowsWithPeople.forEach(personRow => {
         const colValues = Array.from(personRow.querySelectorAll('td'));

         colValues.forEach(val => {

            if (rg.test(val.textContent)) {
               // match, change class
               personRow.className = 'select';
            }
         });
      });

      // clear input
      searchText.value = '';

   }
}

const createPeopleTemplate = (people) => {
   // without classMap directive ...
   return html`
      ${people.map(person => html`
      <tr>
         <td>${person.firstName.concat(' ', person.lastName)}</td>
         <td>${person.email}</td>
         <td>${person.course}</td>
      </tr>`)}
   `;

}

async function loadAllPeople() {

   const people = Object.values(await getAllPeople());
   
   // render people
   render(createPeopleTemplate(people), document.querySelector('.container tbody'));


}

async function getAllPeople() {
   const response = await fetch('http://localhost:3030/jsonstore/advanced/table', {
      method: "get",
      headers: {
         "Content-Type": "application/json"
      }
   });

   if (response.status === 200) {

      const data = await response.json();

      return data;
   }
}

window.addEventListener('load', solve);