function findLargestNUmber(numOne, numTwo, numThree) {
    let result;

    if (numOne >= numTwo && numOne>=numThree) {
        result = numOne;
    } else if (numTwo >= numOne && numTwo >= numThree) {
        result = numTwo
    } else {
        result = numThree;
    }

    console.log(`The largest number is ${result}.`);
}

function testLargestNumber() {

    findLargestNUmber(5, -3, 16);
    findLargestNUmber(-3, -5, -22.5);
}

testLargestNumber();