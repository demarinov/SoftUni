function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   function onClick() {
      
      const searchBox = document.querySelector('#searchField');
      const searchedText = searchBox.value;

      const tableRows = document.querySelectorAll('table tbody tr');

      const rg = new RegExp(searchedText);

      searchBox.value = '';
      

      for(const tableRow of tableRows) {

         tableRow.classList.remove('select');

         for(const col of Array.from(tableRow.children)) {

            const colText = col.textContent;

            if (rg.test(colText)) {

               // tableRow.classList.add('select');
               tableRow.className = 'select';
            }
         }
      }

   }
}