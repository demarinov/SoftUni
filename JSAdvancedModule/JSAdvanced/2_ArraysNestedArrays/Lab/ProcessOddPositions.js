function processOddPositions(arr) {

    let oddFunction = function (item) {

    };
    // odd, doubled, reverse
    let result = arr.filter((item, i) => i % 2 != 0)
    .map((item) => item *2)
    .reverse();

    console.log(result.join(' '));
}

function testProcessOddPositions() {
    processOddPositions([10, 15, 20, 25]);
    processOddPositions([3, 0, 10, 4, 7, 3]);
}

testProcessOddPositions();