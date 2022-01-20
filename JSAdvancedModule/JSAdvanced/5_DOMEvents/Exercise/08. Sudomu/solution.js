function solve() {

    // V1

    const checkButton = document.querySelector('#exercise button');

    const tableRows = Array.from(document.querySelectorAll('table tbody tr'));

    const tableContainer = document.querySelector('#exercise table');

    const clearButton = document.querySelector('#exercise tfoot tr td button:last-child');

    function checkSudomo(tableRows) {

        const arrInput = tableRows;

        for (let i = 0; i < arrInput.length; i++) {
            // check rows
           
            const row = arrInput[i];

            let rowChildren = Array.from(row.children);

            if (Number(Array.from(rowChildren[1].children)[0].value) === Number(Array.from(rowChildren[0].children)[0].value) 
                || Number(Array.from(rowChildren[1].children)[0].value) === Number(Array.from(rowChildren[2].children)[0].value)) {
                return false;
            }

            if (Number(Array.from(rowChildren[0].children)[0].value) === Number(Array.from(rowChildren[2].children)[0].value)) {
                return false;
            }

            const row1 = Array.from(tableRows[0].children);
            const row2 = Array.from(tableRows[1].children);
            const row3 = Array.from(tableRows[2].children);

            // check columns
            if (Number(Array.from(row2[i].children)[0].value) === Number(Array.from(row1[i].children)[0].value) 
                || Number(Array.from(row2[i].children)[0].value) === Number(Array.from(row3[i].children)[0].value)) {
                return false;
            }

            if (Number(Array.from(row1[i].children)[0].value) === Number(Array.from(row3[i].children)[0].value)) {
                return false;
            }

        }

        return true;
    }

    const divCheck = document.querySelector('#check p');
    checkButton.addEventListener('click', (e) => {

        let sudomoValid = checkSudomo(tableRows);

        if (!sudomoValid) {
            divCheck.textContent = "NOP! You are not done yet...";
            divCheck.style.color = 'red';
            tableContainer.style.border = '2px solid red';
        } else {
            divCheck.textContent = "You solve it! Congratulations!";
            divCheck.style.color = 'green';
            tableContainer.style.border = '2px solid green';
        }
    });

    clearButton.addEventListener('click', (e) =>{
        // clear tableRows

        // clear divCheck
        divCheck.textContent = '';
        tableContainer.style.border = 'none';
        divCheck.style.color = '';

        // clear table col content
        for(const tableRow of tableRows) {
            for(const tableCol of Array.from(tableRow.children)) {
                tableCol.children[0].value = '';
            }
        }
    });

}

function solve02() {
    // V2
    const html = {
        row: r =>
            document
                .querySelector(`#exercise > table > tbody > tr:nth-child(${r})`)
                .querySelectorAll("td > input"),
        outputDiv: document.querySelector("#check p"),
        table: document.querySelector("#exercise > table"),
        inputs: document.getElementsByTagName("input"),
    }

    const getDataMatrix = () => {
        const cell = []

        for (let i = 0; i < 3; i++) {
            const values = Array.from(html.row(i + 1)).map(
                x => Number(x.value) || 0
            )
            cell.push(values)
        }

        return cell
    }

    const isValid = m => {

        const checkRepeatance = arr =>
            arr.some((x, i) => arr.slice(i + 1).some(y => y === x))

        for (let i = 0; i < m[0].length; i++) {
            let column = []
            for (let j = 0; j < m.length; j++) {
                column.push(m[j][i])
            }

            if (checkRepeatance(m[i]) || checkRepeatance(column)) return false
            column = []
        }

        return true
    }

    document.addEventListener("click", e => {
        if (e.target.tagName === "BUTTON") {

            const changeOutput = (b = "", m = "", c = "") => {
                html.table.style.border = b
                html.outputDiv.innerHTML = m
                html.outputDiv.style.color = c
            }

            if (e.target.innerHTML.includes("Check")) {
                isValid(getDataMatrix())
                    ? changeOutput(
                          "2px solid green",
                          "You solve it! Congratulations!",
                          "green"
                      )
                    : changeOutput(
                          "2px solid red",
                          "NOP! You are not done yet...",
                          "red"
                      )
            } else {
                changeOutput()
                Array.from(html.inputs).map(x => (x.value = ""))
            }
        }
    })
}