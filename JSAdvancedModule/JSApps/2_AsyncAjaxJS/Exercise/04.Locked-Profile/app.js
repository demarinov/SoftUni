function lockedProfile() {

    const usersPromise = getUsers();

    resolveUsers(usersPromise);

    async function resolveUsers(usersPromise) {
        await usersPromise.then(users => {

            const mainContainer = document.querySelector('#main');
            let count = 0;
            for (const key in users) {
                if (Object.hasOwnProperty.call(users, key)) {
                    const user = users[key];

                    console.log(user);

                    if (count > 0) {
                        const divMain = createElement('div', 'profile', '');

                        const img = createElement('img', 'userIcon', '');
                        img.src = './iconProfile2.png';
                        const labelLock = createElement('label', '', 'Lock');

                        const lockInput = createElement('input', '', '');
                        lockInput.type = 'radio';
                        lockInput.name = 'user'+(count+1)+'Locked';
                        lockInput.value = 'lock';
                        lockInput.checked = true;

                        const labelUnlock = createElement('label', '', 'Unlock');

                        const unlockInput = createElement('input', '', '');
                        unlockInput.type = 'radio';
                        unlockInput.name = 'user'+(count+1)+'Locked';
                        unlockInput.value = 'unlock';
                        unlockInput.checked = false;

                        divMain.appendChild(img);
                        divMain.appendChild(labelLock);
                        divMain.appendChild(lockInput);
                        divMain.appendChild(labelUnlock);
                        divMain.appendChild(unlockInput);

                        const br = createElement('br', '', '');
                        const hr = createElement('hr', '', '');

                        divMain.appendChild(br);
                        divMain.appendChild(hr);

                        const labelUser = createElement('label', '', 'Username');

                        const inputUser = createElement('input', '', '');
                        inputUser.type = 'text';
                        inputUser.name = 'user'+(count+1)+'Username';
                        inputUser.value = user.username;
                        inputUser.disabled = true;
                        inputUser.readonly = true;

                        divMain.appendChild(labelUser);
                        divMain.appendChild(inputUser);

                        const divHidden = createElement('div', '', '');
                        divHidden.id = 'user'+(count+1)+'HiddenFields';

                        const hrHidden = createElement('hr', '', '');

                        divHidden.appendChild(hrHidden);

                        const labelEmail = createElement('label', '', 'Email');

                        const inputEmail = createElement('input', '', '');
                        inputEmail.type = 'email';
                        inputEmail.name = 'user'+(count+1)+'Email';
                        inputEmail.value = user.email;
                        inputEmail.disabled = true;
                        inputEmail.readonly = true;

                        divHidden.appendChild(labelEmail);
                        divHidden.appendChild(inputEmail);

                        const labelAge = createElement('label', '', 'Age');

                        const inputAge = createElement('input', '', '');
                        inputAge.type = 'email';
                        inputAge.name = 'user'+(count+1)+'Age';
                        inputAge.value = user.age;
                        inputAge.disabled = true;
                        inputAge.readonly = true;

                        divHidden.appendChild(labelAge);
                        divHidden.appendChild(inputAge);

                        divMain.appendChild(divHidden);

                        const showButton = createElement('button', '', 'Show more');

                        divMain.appendChild(showButton);

                        mainContainer.appendChild(divMain);
                    } else {
                        // first profile skeleton is provided.

                        const inputUser = Array.from(document.querySelectorAll('.profile input:nth-child(9)'))[0];
                        const divHidden = Array.from(document.querySelectorAll('.profile #user1HiddenFields'))[0];


                        const childrenHidden = Array.from(divHidden.children);
                        const inputAge = childrenHidden[4];
                        const inputEmail = childrenHidden[2];

                        inputUser.value = user.username;
                        inputEmail.value = user.email;
                        inputAge.value = user.age;
                    }

                    count++;

                }
            }
        })
            .catch(e => console.log(e));


        setupUnlockedProfiles();    

        return true;
    }

    function createElement(type, clazz, value) {

        const element = document.createElement(type);

        if (clazz !== '') {
            element.className = clazz;
        }

        element.textContent = value;

        return element;
    }

    async function getUsers() {

        const result = await fetch('http://localhost:3030/jsonstore/advanced/profiles');
        return result.json();
    }

    function setupUnlockedProfiles() {

        const showMoreButtons = Array.from(document.querySelectorAll('.profile button'));

        const inputs = Array.from(document.querySelectorAll('.profile input:nth-child(5)'));
        const divs = document.querySelectorAll('.profile div');

        let count = 0;

        let unlocked = [];
        for (let i = 0; i < inputs.length; i++) {

            if (inputs[i].value === 'unlock') {
                unlocked.push(inputs[i]);
            }
        }

        for (const button of showMoreButtons) {

            console.log(button);
            const div = divs[count];
            const unlockedBut = unlocked[count];

            button.addEventListener('click', (e) => {

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

}