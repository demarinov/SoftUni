function solve() {

    const inputArea = document.querySelector('#exercise textarea');

    const generateButton = document.querySelector('#exercise button');

    const table = document.querySelector('.table tbody');

    const outputArea = document.querySelector('#exercise textarea:nth-child(5)');
    const buyButton = document.querySelector('#exercise button:last-child');

    console.log(buyButton);
    generateButton.addEventListener('click',(e) => {
      let inputArr = [];
      inputArr = JSON.parse(inputArea.value);

        for(const obj of inputArr) {
          console.log(obj);

          const row = document.createElement('tr');
          const nameCol = document.createElement('td');
          nameCol.appendChild(document.createElement('p'));
          nameCol.children[0].textContent = obj.name;
          const imgCol = document.createElement('td');
          const img = document.createElement('img');
          img.src = obj.img;
          imgCol.appendChild(img);
          const priceCol = document.createElement('td');
          priceCol.appendChild(document.createElement('p'));
          priceCol.children[0].textContent = obj.price;
          const decorationCol = document.createElement('td');
          decorationCol.appendChild(document.createElement('p'));
          decorationCol.children[0].textContent = obj.decFactor;

          const checkCol = document.createElement('td');
          const checkBCol = document.createElement('input');
          checkBCol.type = 'checkbox';
          checkCol.appendChild(checkBCol);
          // checkBCol.disabled = true;

          row.appendChild(imgCol);
          row.appendChild(nameCol);
          row.appendChild(priceCol);
          row.appendChild(decorationCol);
          row.appendChild(checkCol);

          table.appendChild(row);
        }

    });

    buyButton.addEventListener('click', (e) => {
      let totalPrice = 0;
      let avgDecFactor = 0;
      let nameResult = [];
      let countChecked = 0;

      outputArea.value = '';

      for(const tableRow of Array.from(table.children)) {
         console.log(tableRow);
         const name = Array.from(Array.from(tableRow.children)[1].children)[0].textContent;
         console.log(name);
         const price =  Number(Array.from(Array.from(tableRow.children)[2].children)[0].textContent);
         
         const decFactor = Number(Array.from(Array.from(tableRow.children)[3].children)[0].textContent);
         const checkedBox = Array.from(Array.from(tableRow.children)[4].children)[0];

         if (checkedBox.checked) {
           console.log('checked');
           nameResult.push(name);
           totalPrice += price;
           avgDecFactor += decFactor;
           countChecked++;
         }
       }

       totalPrice = Number(totalPrice).toFixed(2);
       avgDecFactor = Number(avgDecFactor/countChecked);

       console.log('Bought '+nameResult.join(', '));
       console.log(`Total price: ${totalPrice}`);
       console.log(`Average decoration factor: ${avgDecFactor}`);

       outputArea.value += 'Bought furniture: '+nameResult.join(', ')+'\n';
       outputArea.value += `Total price: ${totalPrice}\n`;
       outputArea.value += `Average decoration factor: ${avgDecFactor}`;
    });
    
}