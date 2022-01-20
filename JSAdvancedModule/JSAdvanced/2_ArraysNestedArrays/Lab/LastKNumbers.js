function kNumbers(n, k) {

    let result = [];
    result[0] = 1;
    for(let i=1; i<n;i++) {
        let bound = i - k;

        if (bound < 0) {
            bound = 0;
        }

        let sumOfNumbers = 0;
        for(let j = i-1; j >= bound; j--) {
            sumOfNumbers += result[j];
        }

        result[i] = sumOfNumbers;
    }

    // console.log(result);
    return result;
}

function testKNumbers() {
    kNumbers(6,3);
    kNumbers(8,2);
}

testKNumbers();