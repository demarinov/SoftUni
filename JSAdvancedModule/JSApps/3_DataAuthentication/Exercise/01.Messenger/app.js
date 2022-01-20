function attachEvents() {
    
    const author = document.querySelector('input[name=author]');
    const content = document.querySelector('input[name=content]');

    const submit = document.querySelector('#submit');   
    const refresh = document.querySelector('#refresh');
    const textArea = document.querySelector('#messages');

    submit.addEventListener("click", sendPostMessage);
    refresh.addEventListener('click',sendGetMessage);

    async function sendGetMessage() {
        const response = await fetch('http://localhost:3030/jsonstore/messenger',
        {
            method: 'get',
            headers: {
                "Content-Type":"application/json"
            }
        });      

        if (response.status === 200) {
            const pureResponse = await response.json();
            // clear textArea
            textArea.value = '';
            Object.values(pureResponse).forEach(r => {
                textArea.value += `${r.author}: ${r.content}\n`;
            });

        } else {
            console.error('error');
        }
    }

    async function sendPostMessage() {

        if (author.value.trim() === '' 
        || content.value.trim() === '') {
            console.error('Cannot send message. Empty values!');
            return;
        }
        const data = {
            "author":author.value,
            "content":content.value
        }
        const response = await fetch('http://localhost:3030/jsonstore/messenger',
        {
            method: 'post',
            headers: {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        });        

        if (response.status === 200) {
            const pureResponse = await response.json();
        } else {
            console.error('error');
        }

        author.value = '';
        content.value = '';
    }
}

attachEvents();