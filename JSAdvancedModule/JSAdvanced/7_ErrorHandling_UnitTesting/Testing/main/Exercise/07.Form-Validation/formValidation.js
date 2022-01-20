function validate() {

    const companyCheckbox = document.querySelector('#company');
    const companyInfo = document.querySelector('#companyInfo');

    companyCheckbox.addEventListener('change', function (e) {

        e.preventDefault();
        if (this.checked) {
            companyInfo.style.display = 'table';

        } else {
            companyInfo.style.display = 'none';
            const companyNumber = document.querySelector('#companyNumber');
            companyNumber.value = '';
        }
    });

    const submit = document.querySelector('#submit');

    submit.addEventListener('click', function (e) {

        e.preventDefault();

        // •The username needs to be between 3 and 20 symbols 
    // inclusively and only letters and numbers are allowed.
    const rgUsername = /^[a-zA-Z0-9]{3,20}$/g;

    // •The password and confirm-password must be between 5 and 15 inclusively symbols 
    // and only word characters are allowed (letters, numbers and _).
    const rgPassword = /^[\w]{5,15}$/g;
    const rgPasswordConfirm = /^[\w]{5,15}$/g;

    //•	The email field must contain the “@” symbol and at least one "."(dot) after it.
    // const rgEmail = /^[\w\-]+@[\w\-]+(\.[\w\-]+)+/g;
    const rgEmail = /^.*@.*\..*$/g;

    //The Company Number field must be a number between 1000 and 9999. When fieldset visible.
    const rgCheckbox = /^[1-9][0-9]{3,3}$/g;

        const validDiv = document.querySelector('#valid');

        let invalidField = false;

        const username = document.querySelector('#username');
        

        if (!rgUsername.test(username.value)) {
            // username.style.border = 'solid';
            username.style['border-color'] = 'red';
            invalidField = true;
        } else {
            username.style.border = 'none';
        }


        //•	The inputs of the password and confirm-password field must match.
        const password = document.querySelector('#password');
        const confirmPassword = document.querySelector('#confirm-password');

        if ((password.value === '' && confirmPassword.value === '')
           || password.value !== confirmPassword.value
           || !rgPassword.test(password.value)
           || !rgPasswordConfirm.test(confirmPassword.value)) {
            // password.style.border = 'solid';
            confirmPassword.style.border = 'solid';
            password.style['border-color'] = 'red';
            confirmPassword.style['border-color'] = 'red';
            invalidField = true;
        } else {
            password.style.border = 'none';
            confirmPassword.style.border = 'none';
        }

        const email = document.querySelector('#email');
        
        if (!rgEmail.test(email.value)) {
            // email.style.border = 'solid';
            email.style['border-color'] = 'red';
            invalidField = true;
        } else {
            email.style.border = 'none';
        }

        if (companyCheckbox.checked) {
            // validate

            const companyNumber = document.querySelector('#companyNumber');
            if (!rgCheckbox.test(companyNumber.value)) {
                // companyNumber.style['border'] = 'solid';
                companyNumber.style['border-color'] = 'red';
                invalidField = true;
            } else {
                companyNumber.style['border'] = 'none';
            }

        } else {
            companyInfo.style.display = 'none';
        }

        if (invalidField) {
            validDiv.style.display = 'none';
        } else {
            validDiv.style.display = 'block';
        }

    });

}
