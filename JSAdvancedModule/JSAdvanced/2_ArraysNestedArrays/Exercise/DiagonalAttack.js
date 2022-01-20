function diagonalAttack(arrStr) {

    let arr = [];
    arrStr.forEach(element => {
        const numStr = element.split(' ');
        arr.push(numStr.map(el => parseInt(el)));
    });

    let diagSum = 0;

    function checkEqualDiagonalSum(arr) {

        let leftDiagSum = 0;

        for(let row = 0; row < arr.length; row++) {

            const rowArray = arr[row];
            const col = row;
            leftDiagSum += rowArray[col];
        }


        let rightDiagSum = 0;
        for(let row = 0; row < arr.length; row++) {

            const rowArray = arr[row];

            const col = arr.length-1 - row;
            rightDiagSum += rowArray[col];
        }


        if (rightDiagSum === leftDiagSum) {
            diagSum = leftDiagSum;
            return true;
        }

        return false;
    }

    const equalDiagSum = checkEqualDiagonalSum(arr);
    if (!equalDiagSum) { 
       arr.forEach(el => console.log(el.join(' ')));
    } else {
        function resetArray(arr,newNum) {

            for(let row = 0; row < arr.length; row++) {

                const rowArray = arr[row];

                let leftDiagCol = row;
                let rightDiagCol = arr.length - 1 - row;

                if (row >= Math.ceil(arr.length/2)) {
                    let temp = leftDiagCol;
                    leftDiagCol = rightDiagCol;
                    rightDiagCol = temp;
                }

                for(let col = 0; col < leftDiagCol; col++) {
                    rowArray[col] = newNum;
                }

                for(let col = leftDiagCol+1; col < rightDiagCol; col++) {
                    rowArray[col] = newNum;
                }

                for(let col = rightDiagCol+1; col < rowArray.length; col++) {
                    rowArray[col] = newNum;
                }
            }
        }

        resetArray(arr,diagSum);
        arr.forEach(el => console.log(el.join(' ')));
    }
}

function testDiagonalAttack() {
    diagonalAttack(['5 3 12 3 1',
    '11 4 23 2 5',
    '101 12 3 21 10',
    '1 4 5 2 2',
    '5 22 33 11 1']
    );

    diagonalAttack(['1 1 1',
    '1 1 1',
    '1 1 0']
    );
}

testDiagonalAttack();