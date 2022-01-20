function extract(content) {
    const contentElement = document.querySelector('#'+content).textContent;

    let rg = new RegExp('\\(([^)]+)\\)','g');

    let match = rg.exec(contentElement);

    let result = '';

    while(match) {
        const el = match[1];
        result += el + '; ';
        match = rg.exec(contentElement);

    }

    // console.log(result);
    return result;
}