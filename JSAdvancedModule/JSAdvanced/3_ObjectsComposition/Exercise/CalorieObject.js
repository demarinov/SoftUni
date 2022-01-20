function getCalorieObject(input) {

    let obj = {};

    for(let i=0; i< input.length; i+=2) {

        let name = input[i];
        let value = Number(input[i+1]);

        obj[name] = value;
    }

    console.log(obj);
}

function testCalorieObject() {

    getCalorieObject(['Yoghurt', '48', 'Rise', '138', 'Apple', '52']);

    getCalorieObject(['Potato', '93', 'Skyr', '63', 'Cucumber', '18', 'Milk', '42']);
}

testCalorieObject();