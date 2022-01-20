function focused() {
    const inputItems = document.querySelectorAll('body div div input');
    
    for(const input of inputItems) {
        input.addEventListener('focus', (e) => {
            let classItem = document.createAttribute('class');
            classItem.value = 'focused';
            e.target.parentNode.setAttributeNode(classItem);    
        
        });

        input.addEventListener('blur', (e) => {
            e.target.parentNode.className = '';
        });
    }
    
}