function fromJSONToHTMLTable(input) {

    // <tr><th>Name</th><th>Score</th></tr>
    //   <tr><td>Stamat</td><td>5.5</td></tr>
    // <tr><td>Rumen</td><td>6</td></tr>

    let result = "<table>\n";

    let arr = JSON.parse(input);

    let count = 0;

    const escapeChar = e =>
        `${e}`
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#39;")

    let headerKeys;
    for (const data of arr) {

        if (count == 0) {
            // get headers
            headerKeys = Object.keys(data);
            result += `\t<tr>`;

            for(const key of headerKeys) {
                result += `<th>${key}</th>`;
            }
            result +='</tr>\n';
        }

        result += '\t<tr>';

        for(const key of headerKeys) {

            result += `<td>${escapeChar(data[key])}</td>`;
        }

        result += '</tr>\n';
        count++;
    }

    result += "</table>";
    return result;

}

function test() {
    console.log(fromJSONToHTMLTable(['[{"Name":"Stamat","Score":5.5},{"Name":"Rumen","Score":6}]']));

    console.log(fromJSONToHTMLTable('[{"Name":"Pesho","Score":4,"Grade":"8"},{"Name":"Gosho","Score":5,"Grade":"8"},{"Name":"Angel","Score":5.5,"Grade":"10"}]'));
}

test();