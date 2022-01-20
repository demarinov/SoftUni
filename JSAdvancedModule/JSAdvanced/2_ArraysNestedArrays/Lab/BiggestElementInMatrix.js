function biggestElement(matrix) {

    let max = matrix[0][0];

    matrix.forEach(row => {
        row.forEach(col => {if (col > max) {max = col;}});
    });

    // console.log(max);
    return max;
}

function testBiggestElement() {
    biggestElement([[20, 50, 10],
        [8, 33, 145]]
       );
    
    biggestElement([[3, 5, 7, 12],
        [-1, 4, 33, 2],
        [8, 3, 0, 4]]
       );
}

testBiggestElement();