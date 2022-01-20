function orbit(arr) {

    const width = arr[0];
    const height = arr[1];
    const x = arr[2];
    const y = arr[3];


    function createEmptySpaceArray(width, height) {

        let result = [];
        for (let row = 0; row < height; row++) {
            const rowArray = [];
            for (let col = 0; col < width; col++) {
                rowArray.push('');
            }

            result.push(rowArray);
        }

        return result;

    }

    let arrOrbit = createEmptySpaceArray(width, height);

    arrOrbit[x][y] = 1;

    // find each diagonal boundary with empty cell(upperleft, downleft, upperright, 
    // downright) from base number, then fill the surrounding space with latest number+1
    // possible combos (UL & DL, UL & UR, UR & DR, DR & DL)
    // if no diagonal boundary case then find the closest edge case (UL, DL, UR, DR)
    // from boundary to boundary fill the cells if empty
    // horizontally or vertically




    fillTheOrbit(arrOrbit, x, y);

    // console.log(getOutput(arrOrbit));

    return getOutput(arrOrbit);

    function getOutput(arrOrbit) {

        let result = '';
        for(let i = 0; i<=arrOrbit.length-1; i++) {

            result += arrOrbit[i].join(' ')+'\n';
        }

        return result;
    }

    function fillTheOrbit(arrOrbit, x, y) {

        function inBoundary(arrOrbitLen, i, j) {

            if (i < 0 || i >= arrOrbitLen || j < 0 || j >= arrOrbitLen) {
                return false;
            }

            return true;
        }

        function getBoundary(arrOrbit, i, j, x, y) {

            let result = [i, j];

            if ((i < 0 || i >= arrOrbit.length) && (j < 0 || j >= arrOrbit.length)) {

                return [x, y];
            }

            if (i < 0) {
                result[0] = 0;
            } else if (i >= arrOrbit.length) {
                result[0] = arrOrbit.length - 1;
            }

            if (j < 0) {
                result[1] = 0;
            } else if (j >= arrOrbit.length) {
                result[1] = arrOrbit.length - 1;
            }

            return result;
        }

        function findEmptyCell(arrOrbit) {

            for(let i = 0; i <= arrOrbit.length-1; i++) {

                for(let j=0; j <= arrOrbit[i].length-1; j++) {
                    if (arrOrbit[i][j] == '')  {
                        return true;
                    }
                }
            }

            return false;
        }

        let count = 0;
        for (;findEmptyCell(arrOrbit);) {

            count += 1;
            let i = x;
            let j = y;

            // find diagonals
            let ul = [i - count, j - count];

            if (!inBoundary(arrOrbit.length, i - count, j - count)) {
                ul = getBoundary(arrOrbit, i - count, j - count, x, y);

                if (ul[0] == x && ul[1] == y) {
                    ul[0] == 0;
                    ul[1] == 0;
                }
            }

            let dl = [i + count, j - count];

            if (!inBoundary(arrOrbit.length, i + count, j - count)) {
                dl = getBoundary(arrOrbit, i + count, j - count, x, y);

                if (dl[0] == x && dl[1] == y) {
                    dl[0] = arrOrbit.length-1;
                    dl[1] = 0;
                }
            }

            let ur = [i - count, j + count];

            if (!inBoundary(arrOrbit.length, i - count, j + count)) {
                ur = getBoundary(arrOrbit, i - count, j + count, x, y);

                if (ur[0] == x && ur[1] == y) {
                    ur[0] = 0;
                    ur[1] = arrOrbit.length-1;
                }
            }

            let dr = [i + count, j + count];

            if (!inBoundary(arrOrbit.length, i + count, j + count)) {
                dr = getBoundary(arrOrbit, i + count, j + count, x, y);

                if (dr[0] == x && dr[1] == y) {
                    dr[0] = arrOrbit.length-1;
                    dr[1] = arrOrbit.length-1;
                }
            }

            // ul & dl
            for (let i = 0; i <= dl[0] - ul[0]; i++) {

                if (ul[0] + i == x && ul[1] == y) {
                    continue;
                }

                if (arrOrbit[ul[0] + i][ul[1]] === '') {
                    arrOrbit[ul[0] + i][ul[1]] = 1 + count;
                }

            }

            // ul & ur
            for (let i = 0; i <= ur[1] - ul[1]; i++) {

                if (ul[0] == x && ul[1] + i == y) {
                    continue;
                }

                if (arrOrbit[ul[0]][ul[1] + i] === '') {
                    arrOrbit[ul[0]][ul[1] + i] = 1 + count;
                }

            }

            // ur & dr
            for (let i = 0; i <= dr[0] - ur[0]; i++) {

                if (ur[0] + i == x && ur[1] == y) {
                    continue;
                }

                if (arrOrbit[ur[0] + i][ur[1]] === '') {
                    arrOrbit[ur[0] + i][ur[1]] = 1 + count;
                }

            }

            // dl & dr
            for (let i = 0; i <= dr[1] - dl[1]; i++) {

                if (dl[0] == x && dl[1] + i == y) {
                    continue;
                }

                if (arrOrbit[dl[0]][dl[1] + i] === '') {
                    arrOrbit[dl[0]][dl[1] + i] = 1 + count;
                }

            }

        }
    }
}


function testOrbit() {

    console.log(orbit([4, 4, 0, 0]));
    
    console.log(orbit([5, 5, 2, 2]));

    console.log(orbit([3, 3, 2, 2]))

    console.log(orbit([5, 5, 0, 0]));

    console.log(orbit([5, 5, 1, 2]));
    
    console.log(orbit([5, 5, 2, 1]));
}

testOrbit();