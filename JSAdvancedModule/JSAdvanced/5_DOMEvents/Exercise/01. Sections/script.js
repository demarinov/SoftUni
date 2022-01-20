function create(words) {
   
   const mainDiv = document.querySelector('#content');
   for(const word of words) {
      const div = document.createElement('div');
      const p  =document.createElement('p');
      p.textContent = word;
      div.appendChild(p);

      div.addEventListener('click', (e) =>{
         e.target.children[0].style.display = 'block';
      });

      p.style.display = 'none';

      mainDiv.appendChild(div);
   }


}