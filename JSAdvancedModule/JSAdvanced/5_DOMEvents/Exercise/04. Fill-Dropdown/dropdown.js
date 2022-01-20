function addItem() {
    
    const itemText = document.querySelector('#newItemText');
    const itemValue = document.querySelector('#newItemValue');

    const selectMenu = document.querySelector('#menu');

    const option = document.createElement('option');

    option.textContent = itemText.value;
    option.value = itemValue.value;

    selectMenu.appendChild(option);

    itemText.value = '';
    itemValue.value = '';

}