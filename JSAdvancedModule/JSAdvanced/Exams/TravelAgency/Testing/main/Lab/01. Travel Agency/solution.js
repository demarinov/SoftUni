window.addEventListener('load', solution);

function solution() {

  const fname = document.querySelector('#fname');
  const email = document.querySelector('#email');
  const phone = document.querySelector('#phone');
  const address = document.querySelector('#address');
  const code = document.querySelector('#code');

  const submitBtn = document.querySelector('#submitBTN');

  submitBtn.addEventListener('click', function (e) {

    if (fname.value === '' || email.value === '') {
      return;
    } 

    const previewList = document.querySelector('#infoPreview');

    const labels = document.querySelectorAll('#form div label');

    const listItemFname = createElement('li','',labels[0].textContent+' '+fname.value);
    const listItemEmail = createElement('li','',labels[1].textContent+' '+email.value);
    const listItemPhone = createElement('li','',labels[2].textContent+' '+phone.value);
    const listItemAddress = createElement('li','',labels[3].textContent+' '+address.value);
    const listItemCode = createElement('li','',labels[4].textContent+' '+code.value);

    previewList.appendChild(listItemFname);
    previewList.appendChild(listItemEmail);
    if (phone.value !== '') {
      previewList.appendChild(listItemPhone);
    }
    if (address.value !== '') {
      previewList.appendChild(listItemAddress);
    }
    if (code.value !== '') {
      previewList.appendChild(listItemCode);
    }

    e.target.disabled = true;

    // clear input fields
    fname.value = '';
    email.value = '';
    phone.value = '';
    address.value = '';
    code.value = '';

    // activate action buttons
    const editBtn = document.querySelector('#editBTN');
    const continueBtn = document.querySelector('#continueBTN');
    
    editBtn.disabled = false;
    continueBtn.disabled = false;

    editBtn.addEventListener('click', function(e) {
      fname.value = listItemFname.textContent.replace(labels[0].textContent,'');
      email.value = listItemEmail.textContent.replace(labels[1].textContent,'');
      phone.value = listItemPhone.textContent.replace(labels[2].textContent, '');
      address.value = listItemAddress.textContent.replace(labels[3].textContent,'');
      code.value = listItemCode.textContent.replace(labels[4].textContent,'');

      // remove items from list
      Array.from(previewList.children).forEach(c => c.parentElement.removeChild(c));
      
      // enable action buttons
      editBtn.disabled = true;
      continueBtn.disabled = true;

      submitBtn.disabled = false;

    });

    continueBtn.addEventListener('click', function(e) {
      const blockDiv= document.querySelector('#block');

      blockDiv.innerHTML = '';

      const h3 = createElement('h3','','Thank you for your reservation!');

      blockDiv.appendChild(h3);
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
