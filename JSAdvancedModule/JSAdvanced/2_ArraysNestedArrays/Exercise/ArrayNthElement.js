function arrayNthElement(arr, step) {

    let nextStep = step;
    let result = [];

    result.push(arr[0]);

    for(let i = 1; i < arr.length;i++) {

        if (i === nextStep) {

            result.push(arr[i]);
            nextStep += step;
        }
    }

    return result;
}


function testArrayNthElement() {
    console.log(arrayNthElement(['5', 
    '20', 
    '31', 
    '4', 
    '20'], 
    2
    ));


    console.log(arrayNthElement(['dsa',
    'asd', 
    'test', 
    'tset'], 
    2
    ));

    console.log(arrayNthElement(['1', 
    '2',
    '3', 
    '4', 
    '5'], 
    6
    ));
}

testArrayNthElement();