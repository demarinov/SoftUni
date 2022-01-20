function townsToJson(input) {


    let headings = input[0].split('|');

    let result = [];

    for(let i = 1; i < input.length; i++) {
        let town = input[i].split('|');

        let townObj = {};
        townObj[headings[1].trim()] = town[1].trim();
        townObj[headings[2].trim()] = Number(Number(town[2].trim()).toFixed(2));
        townObj[headings[3].trim()] = Number(Number(town[3].trim()).toFixed(2));
        result.push(townObj);
    }

    console.log(JSON.stringify(result));
}

function test() {
    townsToJson(['| Town | Latitude | Longitude |',
    '| Sofia | 42.696552 | 23.32601 |',
    '| Beijing | 39.913818 | 116.363625 |']
    );

    townsToJson(['| Town | Latitude | Longitude |',
    '| Veliko Turnovo | 43.0757 | 25.6172 |',
    '| Monatevideo | 34.50 | 56.11 |']
    );
}

test();