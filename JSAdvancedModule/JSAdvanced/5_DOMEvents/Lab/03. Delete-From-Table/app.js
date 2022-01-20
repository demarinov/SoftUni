function deleteByEmail() {

    const result = document.querySelector('#result');
    const inputText = document.querySelector('label input').value;
    result.textContent = '';

    if (inputText.length == 0) {
        return;
    }

    
    let foundEntry = false;
    let count = 0;

    // deleteByEmailV1();
    function deleteByEmailV1() {
        const tableRows = document.querySelectorAll('#customers tr');

        for (const tableRow of tableRows) {

            if (count == 0) {
                count++;
                continue;
            }

            console.log(tableRow.children[1].textContent);
            const matched =
                tableRow.children[1].textContent == inputText;

            if (matched) {

                // delete row
                tableRow.parentNode.removeChild(tableRow);
                result.textContent = 'Deleted';
                foundEntry = matched;
                break;
            }

            count++;
        }
    }

    deleteByEmailV2();

    function deleteByEmailV2() {
        const secondColumn = document.querySelectorAll('#customers tr td:nth-child(2)');
        let foundEntry = false;

        for (const col of secondColumn) {
            if (inputText == col.textContent) {
                const row = col.parentNode;
                row.parentNode.removeChild(row);
                result.textContent = 'Deleted';
                foundEntry = true;
                break;
            }
        }
    }

    if (!foundEntry) {
        result.textContent = 'Not found.';
    }


}