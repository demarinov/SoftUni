
function sameNumbers(num) {

    if (num < 0) {
        console.log(false);
        console.log(NaN);
        return;
    }

    let sum = 0;

    let tempNum = num;
    let rem = tempNum % 10;
    let firstNum = rem;
    let allTheSame = true;
    // case 10 % 10 to consider usually ...
    while(tempNum >= 1) {

        sum += rem;
        

        if (rem != firstNum) {
            allTheSame = false;
        }

        tempNum = Math.floor(tempNum / 10);
        rem = tempNum % 10;    
    }
    
    console.log(allTheSame);
    console.log(sum);
}

function testSameNumbers() {
    sameNumbers(2222222);
    sameNumbers(1234);

    sameNumbers(112);

    sameNumbers(-1);

    sameNumbers(0);
}

testSameNumbers();

function solve(input) {
    let arr = String(input).split('').map(n => Number(n));
    let isSame = arr.filter((n) => n != arr[0]);
    let sum = arr.reduce((acc, num) => acc + num);
    let result = isSame.length == 0 ? true : false;
    console.log(result);
    console.log(sum);
}

function testSolve() {
    solve(2222222);
    solve(1234);

    solve(112);
    solve(-11);
    solve(0);
}

testSolve();