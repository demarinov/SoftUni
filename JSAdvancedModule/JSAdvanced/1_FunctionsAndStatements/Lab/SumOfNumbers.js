function sumOfNumbers(n, m) {

    const numOne = Number(n);
    const numTwo = Number(m);

    let result = 0; 
    for (let i = numOne; i <=numTwo; i++) {
       result += i;
    }

    console.log(result);
}

function testSumOfNumbers() {
    sumOfNumbers('1', '5');
    sumOfNumbers('-8', '20');
}

testSumOfNumbers();