function solve(arr, order) {

    let obj = {

        'asc': (arr) => {
            arr.sort((a,b) =>a-b);
        },
        'desc': (arr) => {
            arr.sort((a,b) => b-a);
        }
    }

    obj[order](arr);

    return arr;
}


function test() {

    console.log(solve([14, 7, 17, 6, 8], 'asc'));
    console.log(solve([14, 7, 17, 6, 8], 'desc'));
}

test();