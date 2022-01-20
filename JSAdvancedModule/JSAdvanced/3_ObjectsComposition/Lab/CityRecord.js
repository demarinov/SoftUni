function cityRecord(name, population, treasury) {

    return {
        name,
        population,
        treasury
    };
}

function testCityRecord() {
    
    let result;
    result = cityRecord('Tortuga',
    7000,
    15000
    );

    console.log(result);

    result = cityRecord('Santo Domingo',
    12000,
    23500
    );

    console.log(result);
}

testCityRecord();
