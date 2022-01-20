function attachEventsListeners() {

    const inputTextDays = document.querySelector('#days');
    const inputTextHours = document.querySelector('#hours');
    const inputTextMinutes = document.querySelector('#minutes');
    const inputTextSeconds = document.querySelector('#seconds');


    const inputButtonDays = document.querySelector('#daysBtn');
    checkButton(inputButtonDays);
    const inputButtonHours = document.querySelector('#hoursBtn');
    checkButton(inputButtonHours);
    const inputButtonMinutes = document.querySelector('#minutesBtn');
    checkButton(inputButtonMinutes);
    const inputButtonSeconds = document.querySelector('#secondsBtn');
    checkButton(inputButtonSeconds);

    function checkButton(button) {
        button.addEventListener('click', (e) => {
            
            // get input
            let input;
            let toSeconds = -1;
            
            if (inputTextDays.value !== '') {
                input = Number(inputTextDays.value);
                toSeconds = input * 24 * 60 * 60;
            } else if (inputTextHours.value !== '') {
                input = Number(inputTextHours.value);
                toSeconds = input * 60 * 60;
            } else if (inputTextMinutes.value !== '') {
                input = Number(inputTextMinutes.value);
                toSeconds = input * 60;
            } else if (inputTextSeconds.value !== '') {
                input = Number(inputTextSeconds.value);
                toSeconds = input;
            }

            inputTextDays.value = toSeconds / 60 / 60 / 24;
            inputTextHours.value = toSeconds / 60 / 60;
            inputTextMinutes.value = toSeconds / 60;
            inputTextSeconds.value = toSeconds;
        });
    }
}