
function editElement(htmlRef, match, replacer) {
    let newText = htmlRef.textContent;
    let rg = new RegExp(match, 'g');
    newText = newText.replace(rg, replacer);
    htmlRef.textContent = newText;
}
