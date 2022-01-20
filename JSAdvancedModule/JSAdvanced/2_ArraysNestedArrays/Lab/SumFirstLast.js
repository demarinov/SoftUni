function sumFirstLast(arr) {

    let sum = 0;

    sum = Number(arr[0]) + Number(arr[arr.length-1]);

    // console.log(sum);
    return sum;
}

function testSumFirstLast() {
    sumFirstLast(['20', '30', '40']);
    sumFirstLast(['5', '10']);
}

testSumFirstLast();