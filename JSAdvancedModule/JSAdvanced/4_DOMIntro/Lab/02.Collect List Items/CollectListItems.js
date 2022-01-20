function extractText() {
    

    // const list = document.getElementById('items');
    // const textArea = document.getElementById('result');

    const list = document.querySelectorAll('ul#items li');
    const textArea = document.querySelector('#result');

    let listResult = '';
    for(const li of Array.from(list)) {
        listResult += li.textContent + '\n';
    }

    textArea.value = listResult;
}