function sortNumbers(arr) {

    let result = [];

    arr.sort((a,b)=>a-b);
    while(arr.length > 0) {
        const first = arr.shift();
        const second = arr.pop();
        result.push(first);
        result.push(second);
    }

    return result;
}

function testSortNumbers() {

    let res = sortNumbers([1, 65, 3, 52, 48, 63, 31, -3, 18, 56]);
    console.log(res);

    res = sortNumbers([]);
    console.log(res);
}

testSortNumbers();