function findSmallestNumbers(arr) {

    arr.sort((a, b) => a-b);

    let result = [];

    result[0] = arr[0];
    result[1] = arr[1];
    console.log(result.join(' '));
}

function testSmallestNUmber() {
    findSmallestNumbers([30, 15, 50, 5]);
    findSmallestNumbers([3, 0, 10, 4, 7, 3]);
}

testSmallestNUmber();