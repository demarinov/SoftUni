function magicMatrix(matrix) {

    let comparisonResult;

    let isMagic = true;
    // check columns
    for(let row = 0; row<matrix.length; row++) {

        let sum = 0;
        for(let col = 0; col < matrix[row].length;col++) {
            sum += matrix[row][col];
            
        }

        if (comparisonResult == undefined) {
            comparisonResult = sum;
        }

        if (comparisonResult !== sum) {
            isMagic = false;
            break;
        }
    }

    // check rows
    if (isMagic) {
        for (let col = 0; col < matrix[0].length; col++) {
            
            let sum = 0;
            for (let row = 0; row < matrix.length; row++) {
                sum += matrix[row][col];
            }

            
            if (comparisonResult !== sum) {
                isMagic = false;
                break;
            }
        }
    }

    console.log(isMagic);
}

function testMagicMatrix() {

    // magicMatrix([[4, 5, 6],
    //     [6, 5, 4],
    //     [5, 5, 5]]
    //    );

    // magicMatrix([[11, 32, 45],
    //     [21, 0, 1],
    //     [21, 1, 1]]
    //    );   
    // magicMatrix([[1, 0, 0],
    //     [0, 0, 1],
    //     [0, 1, 0]]
    //    );   
    magicMatrix([[]]);
}

testMagicMatrix();