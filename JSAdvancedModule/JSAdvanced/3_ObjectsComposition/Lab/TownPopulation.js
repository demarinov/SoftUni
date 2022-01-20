function townPopulation(inputArr) {

    let result = [];

    for (const input of inputArr) {

        const tempArr = input.split(' <-> ');

        const name = tempArr[0];
        const population = Number(tempArr[1]);

        let town = result.find((a) => a.name === name);

        if (town !== undefined) {
            town.population += population;
        } else {
            result.push({ name, population});
        }

    }

    for(const output of result) {
        console.log(output.name +' : '+output.population);
    }
}

function testTownPopulation() {
    townPopulation(['Sofia <-> 1200000',
    'Montana <-> 20000',
    'New York <-> 10000000',
    'Washington <-> 2345000',
    'Las Vegas <-> 1000000']
    );

    townPopulation(['Istanbul <-> 100000',
    'Honk Kong <-> 2100004',
    'Jerusalem <-> 2352344',
    'Mexico City <-> 23401925',
    'Istanbul <-> 1000']
    );
}

testTownPopulation();


function towns(input) {

    const towns = {};

    for(const townData of input) {

        let [name, population] = townData.split(' <-> ');

        if (towns[name] !== undefined) {
            population += towns[name];
        }

        towns[name] = population;
    }

    for(const name in towns) {
        console.log(`${name} : ${towns[name]}`);
    }
}

function testTowns() {

    towns(['Sofia <-> 1200000',
    'Montana <-> 20000',
    'New York <-> 10000000',
    'Washington <-> 2345000',
    'Las Vegas <-> 1000000']
    );

    towns(['Istanbul <-> 100000',
    'Honk Kong <-> 2100004',
    'Jerusalem <-> 2352344',
    'Mexico City <-> 23401925',
    'Istanbul <-> 1000']
    );
}

testTowns();