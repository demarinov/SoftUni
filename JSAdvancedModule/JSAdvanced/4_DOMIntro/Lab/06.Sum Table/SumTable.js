function sumTable() {

    const sum = document.querySelector('td#sum');

    const tableRows = Array.from(document.querySelectorAll('table tr'));

    let total = 0;
    for(let i = 1; i < tableRows.length; i++) {

        const cols = Array.from(tableRows[i].children);
        const cost = cols[cols.length - 1].textContent;

        total += Number(cost);
    }

    sum.textContent = total;
}