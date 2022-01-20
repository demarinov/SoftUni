function lockedProfile() {

    const showMoreButtons = document.querySelectorAll('.profile button');

    const inputs = Array.from(document.querySelectorAll('.profile input:nth-child(5)'));
    const divs = document.querySelectorAll('.profile div');

    let count = 0;

    let unlocked = [];
    for(let i = 0; i < inputs.length; i++) {

        if (inputs[i].value === 'unlock') {
            unlocked.push(inputs[i]);
        }
    }

    for(const button of showMoreButtons) {

        const div = divs[count]; 
        const unlockedBut = unlocked[count];
        
        button.addEventListener('click',  (e) => {
            
            if (button.textContent === 'Show more') {

                if (unlockedBut.checked) {
                    div.style.display = 'block';
                    button.textContent = 'Hide it';
                }
            } else if (button.textContent === 'Hide it') {
                if (unlockedBut.checked) {
                    div.style.display = 'none';
                    button.textContent = 'Show more';
                }
            }

        });

        count++;
        
    }    


}