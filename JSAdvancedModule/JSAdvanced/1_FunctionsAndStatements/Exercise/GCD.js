
function gcd(numOne, numTwo) {

    let min = numOne;
    let max = numTwo;

    if (numOne > numTwo) {
        max = numOne;
        min = numTwo;
    }

    let result;
    for(let i = min;i>=0;i--) {
        if (max % i == 0 && min % i == 0) {
            result = i;
            break;
        }
    }

    console.log(result);
}

function gcdV2(numOne, numTwo) {

    let min = numOne;
    let max = numTwo;

    if (numOne > numTwo) {
        max = numOne;
        min = numTwo;
    }

    let result;
    
    let rem = max % min;
    while(rem != 0) {

        if (rem < min) {
           max = min; 
           min = rem;    
        }

        rem = max % min;
    }

    result = min;
    console.log(result);
}

function gcdV3(numOne, numTwo) {

    let min = numOne;
    let max = numTwo;

    if (numOne > numTwo) {
        max = numOne;
        min = numTwo;
    }
    
    let rem = max % min;

    if (rem == 0) {
        let result = min;
        console.log(result);
        return;
    }

    if (rem < min) {
        max = min; 
        min = rem;    
    }

    gcdV3(max, min);
}

function testGcd() {

    gcd(15, 5);
    gcd(2154, 458);

    gcdV2(15, 5);
    gcdV2(2154, 458);

    gcdV3(15, 5);
    gcdV3(2154, 458);
}

testGcd();