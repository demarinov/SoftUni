

import {towns} from './towns.js';
import {html, render} from '../../../node_modules/lit-html/lit-html.js';

window.addEventListener('load', function(e) {

   render(createTownTemplate(towns), document.querySelector('#towns'));

   const searchBtn = document.querySelector('article button');

   searchBtn.addEventListener('click', (ev) => {

      const searchText = document.querySelector('#searchText').value;
      search(searchText);
   });
});

const createTownTemplate = (towns) => {

   return html `<ul>
   ${towns.map(town => html `
   <li>${town}</li>`)};
</ul>`;
}

function search(searchText) {
   
   const towns = Array.from(document.querySelectorAll('#towns ul li'));

   const rg = new RegExp(searchText,'i');
   let countMatches = 0;
   towns.forEach(town => {

      if (rg.test(town.textContent)) {
         town.className = 'active';
         countMatches++;
      }
   });

   const result = document.querySelector('#result');
   result.textContent = countMatches + ' matches found';

}
