function findPairs(matrix) {


    let count = 0;

    matrix.forEach((row, rowIdx) => {

        row.forEach((col,colIdx) => {
            if (rowIdx != matrix.length-1) {
                if (col === matrix[rowIdx+1][colIdx]) {
                    count++;
                }
            }

            if (colIdx != row.length-1) {
                if (col === matrix[rowIdx][colIdx+1]) {
                    count++;
                }
                
            }
        });
    })

    // console.log(count);

    return count;
}

function testFindPairs() {
    findPairs([['2', '3', '4', '7', '0'],
    ['4', '0', '5', '3', '4'],
    ['2', '3', '5', '4', '2'],
    ['9', '8', '7', '5', '4']]
   );

   findPairs([['test', 'yes', 'yo', 'ho'],
   ['well', 'done', 'yo', '6'],
   ['not', 'done', 'yet', '5']]
  );
}

testFindPairs();