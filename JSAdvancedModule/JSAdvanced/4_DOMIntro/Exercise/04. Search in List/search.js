function search() {
   
   const searchText = document.querySelector('#searchText').value;
   const towns = document.querySelectorAll('#towns li');

   let rg = new RegExp(searchText);

   let countMatches = 0;
   for(const town of towns) {

      const match = rg.test(town.textContent);

      if (match) {
         countMatches++;
         town.style['font-weight'] = 'bold';
         town.style['text-decoration'] = 'underline';
      }

   }

   const result = document.querySelector('#result');
   result.textContent = countMatches + ' matches found';
}
