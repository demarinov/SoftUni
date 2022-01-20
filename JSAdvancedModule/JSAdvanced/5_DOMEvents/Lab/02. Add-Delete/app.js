function addItem() {
    const newText = document.querySelector('#newItemText');

    if (newText.value.length == 0) {
        return;
    }

    const items = document.querySelector('#items');

    const newItem = document.createElement('li');
    const newTextItem = document.createTextNode(newText.value);
    newItem.appendChild(newTextItem);
    items.appendChild(newItem);

    const link = document.createElement('a');
    link.href='#';
    link.textContent = '[Delete]';
    newItem.appendChild(link);

    link.addEventListener('click', () => {
        link.parentNode.parentNode.removeChild(link.parentNode);
    });
}