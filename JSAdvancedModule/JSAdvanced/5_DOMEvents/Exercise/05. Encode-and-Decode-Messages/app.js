function encodeAndDecodeMessages() {
    const divs = document.querySelectorAll('#main div');

    const senderTextArea = divs[0].children[1];
    const receiverTextArea = divs[1].children[1];

    const senderButton = divs[0].children[2];
    const receiverButton = divs[1].children[2];

    senderButton.addEventListener('click',(e) =>{

        receiverTextArea.value = '';
        
        for(let i = 0; i < senderTextArea.value.length; i++) {
            const c = senderTextArea.value[i];
            receiverTextArea.value += String.fromCharCode(c.charCodeAt(0)+1); 
        }

        senderTextArea.value = '';
    });

    receiverButton.addEventListener('click', (e) => {
        let result = '';
        
        for(let i=0; i < receiverTextArea.value.length; i++) {
            const c = receiverTextArea.value[i];
            result += String.fromCharCode(c.charCodeAt(0)-1);
        }

        receiverTextArea.value = result;
    });

}