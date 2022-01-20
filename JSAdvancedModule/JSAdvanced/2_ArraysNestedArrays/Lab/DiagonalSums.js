function diagonalSums(matrix) {

    let rowIndex = 0;
    let colIndex = rowIndex;

    let sumLeftDiagonal = 0;
    while(rowIndex <= matrix.length-1) {
        sumLeftDiagonal += matrix[rowIndex][colIndex];
        rowIndex++;
        colIndex++;
    }

    let sumRightDiagonal = 0;
    rowIndex = 0;
    colIndex = matrix[0].length-1;

    while(rowIndex <= matrix.length-1) {
        sumRightDiagonal += matrix[rowIndex][colIndex];
        rowIndex++;
        colIndex--;
    }

    console.log(`${sumLeftDiagonal} ${sumRightDiagonal}`);
}  

function testSumDiagonals() {
    diagonalSums([[20, 40],
        [10, 60]]
       );
    
       diagonalSums([[3, 5, 17],
        [-1, 7, 14],
        [1, -8, 89]]
       );

}

testSumDiagonals();