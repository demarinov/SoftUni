function toggle() {

    const div = document.querySelector('#extra');
    const button = document.querySelector('.button');

    if (button.textContent === 'More') {
        button.textContent = 'Less';
        div.style.display = 'block';
    } else if (button.textContent === 'Less') {
        button.textContent = 'More';
        div.style.display = 'none';
    }
}