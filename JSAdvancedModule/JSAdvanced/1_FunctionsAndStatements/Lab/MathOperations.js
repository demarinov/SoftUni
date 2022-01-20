function mathOps(numOne, numTwo, str) {

    let result;

    switch(str) {
        case '+': result = numOne + numTwo; break;
        case '-': result = numOne - numTwo; break;
        case '*': result = numOne * numTwo; break;
        case '/': result = numOne / numTwo; break;
        case '%': result = numOne % numTwo; break;
        case '**': result = numOne ** numTwo; break;

    }

    console.log(result);
}

function testMathOps() {
    mathOps(5, 6, '+');
    mathOps(3, 5.5, '*');

}

testMathOps();