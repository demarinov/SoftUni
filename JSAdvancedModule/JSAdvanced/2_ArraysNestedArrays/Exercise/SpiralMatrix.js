function spiralMatrix(rows, cols) {

    let rowBoundary = rows;
    let colBoundary = cols;

    let startX = 0;
    let startY = 0;
    let counter = 0;


    function createEmptySpaceArray(width, height) {

        let result = [];
        for(let row = 0; row < height; row++) {
            const rowArray = [];
            for(let col = 0; col < width; col++) {
                rowArray.push(' ');
            }

            result.push(rowArray);
        }

        return result;
    }

    let matrix = createEmptySpaceArray(cols, rows);
    while(rowBoundary > 0 && colBoundary > 0) {

        // go right
        for (let col = 1; col <= colBoundary; col++) {
            counter++;
            matrix[startY][startX] = counter;
            startX++;
        }

        startX--;
        rowBoundary--;
        startY++;

        // go down
        for(let row = 1; row <= rowBoundary; row++) {
            counter++;
            matrix[startY][startX] = counter;
            startY++;
        }

        startY--;
        colBoundary--;
        startX--;

        // go left
        for (let col = 1; col <= colBoundary; col++) {
            counter++;
            matrix[startY][startX] = counter;
            startX--;
        }

        startX++;
        rowBoundary--;
        startY--;

        // go up
        for(let row = 1; row <= rowBoundary; row++) {
            counter++;
            matrix[startY][startX] = counter;
            startY--;
        }

        startY++;
        colBoundary--;
        startX++;

    }

    matrix.forEach(row => console.log(row.join(' ')));
}

function testSpiralMatrix() {
    spiralMatrix(5,5);
    spiralMatrix(3,3);
    spiralMatrix(-1,-1);
}

testSpiralMatrix();