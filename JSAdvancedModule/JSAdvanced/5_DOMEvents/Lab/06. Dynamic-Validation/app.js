function validate() {

    const email = document.querySelector('#email');
    const rg = new RegExp('[a-z]+@[a-z]+\\.[a-z]+');

    email.addEventListener('change', (e) => {
        if (!rg.test(email.value)) {
            email.className = 'error';
        } else {
            email.className = '';
        }
    });
}