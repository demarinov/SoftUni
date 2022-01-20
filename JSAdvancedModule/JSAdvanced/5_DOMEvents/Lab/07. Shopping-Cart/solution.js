function solve() {
   const products = document.querySelectorAll('.product');

   const textArea = document.querySelector('textarea');

   let productCache = [];
   let buttonCache = [];
   for(const product of products) {

      const name = product.children[1].children[0].textContent;
      const price = Number(product.children[3].textContent).toFixed(2);

      const addButton = product.children[2].children[0];

      buttonCache.push(addButton);
      addButton.addEventListener('click', (e) =>{
         textArea.textContent += `Added ${name} for ${price} to the cart.\n`;
         if (productCache.some((p) => p.name === name)) {
            const cachedProduct = productCache.find((p) => p.name === name);
            cachedProduct.price = Number(cachedProduct.price) + Number(price);
         } else {
            productCache.push({name, price: Number(price)});
         }
      });
      
   }

   const buttonCheckout = document.querySelector('.checkout');

   buttonCheckout.addEventListener('click', (e) =>{
      const names = productCache.map((a) => a.name).join(', ');
      let totalPrice = 0;
      productCache.forEach((a) => totalPrice+=Number(a.price));
      totalPrice = Number(totalPrice).toFixed(2);
      textArea.textContent += `You bought ${names} for ${totalPrice}.`;
      buttonCache.push(e.target);

      buttonCache.forEach((b) => b.disabled = true);
   })
}