function validate() {
    
    const rgEmail = /^[a-z_\-]+@[a-z_\-]+\.[a-z_\-]+/g;

    const emailInput = document.querySelector('#email');


    emailInput.addEventListener('change', function (e) {
        if (!rgEmail.test(emailInput.value)) {
            e.target.className = 'error';
        } else {
            e.target.className = '';
        }
    });
    
}