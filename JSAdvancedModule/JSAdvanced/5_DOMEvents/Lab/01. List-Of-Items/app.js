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

    
}