function solve() {

   const creatorField = document.querySelector('#creator');
   const titleField = document.querySelector('#title');
   const categoryField = document.querySelector('#category');
   const contentField = document.querySelector('#content');
   const createButton = document.querySelector('form button');

   const postsSection = document.querySelector('main section');

   createButton.addEventListener('click', function (e) {

      e.preventDefault();
      // if (creatorField.value === '' || titleField.value === ''
      //    || categoryField.value === '' || contentField.value === '') {

      //    return;
      // }

      // create new article
      const article = createElement('article', '', '');
      const title = createElement('h1', '', titleField.value);

      const categoryMain = createElement('p', '', 'Category: ');
      const categoryStrong = createElement('strong', '', categoryField.value);

      const creatorMain = createElement('p', '', 'Creator: ');
      const creatorStrong = createElement('strong', '', creatorField.value);

      const content = createElement('p', '', contentField.value);

      const buttons = createElement('div', 'buttons', '');
      const buttonDelete = createElement('button', 'btn delete', 'Delete');
      const buttonArchive = createElement('button', 'btn archive', 'Archive');

      article.appendChild(title);
      categoryMain.appendChild(categoryStrong);
      article.appendChild(categoryMain);
      creatorMain.appendChild(creatorStrong);
      article.appendChild(creatorMain);
      article.appendChild(content);
      buttons.appendChild(buttonDelete);
      buttons.appendChild(buttonArchive);
      article.appendChild(buttons);

      postsSection.appendChild(article);

      // clear fields
      creatorField.value = '';
      titleField.value = '';
      categoryField.value = '';
      contentField.value = '';


      buttonArchive.addEventListener('click', function (e) {

         let listArchive = document.querySelector('.archive-section ol');

         // if (!listArchive) {
         //    listArchive = createElement('ol', '', '');
         // }

         const title = Array.from(e.target.parentElement.parentElement.children)[0];
         const itemArchive = createElement('li', '', title.textContent);

         listArchive.appendChild(itemArchive);

         buttonDelete.click();

         // sort the list
         const sortedList = Array.from(listArchive.children)
            .sort((a1, a2) => {
               return a1.textContent.localeCompare(a2.textContent);
            });

         // console.log(sortedList);

         Array.from(listArchive.children).forEach(e => e.parentElement.removeChild(e));

         sortedList.forEach(e => listArchive.appendChild(e));

      });

      buttonDelete.addEventListener('click', function (e) {
         article.parentNode.removeChild(article);
      });
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
