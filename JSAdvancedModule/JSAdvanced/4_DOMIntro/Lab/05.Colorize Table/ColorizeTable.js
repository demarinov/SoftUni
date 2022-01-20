function colorize() {
    const tableRows = Array.from(document.querySelectorAll('table tr'));

    let i = 0;
    for(const row of tableRows) {
        i++;
        if (i % 2 == 0) {
            row.style.backgroundColor='teal';
        }
    }
}