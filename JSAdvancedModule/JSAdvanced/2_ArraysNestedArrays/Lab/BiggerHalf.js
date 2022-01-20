function biggerHalf(arr) {

    arr.sort((a,b) => a-b);

    let mid = Math.floor(arr.length / 2);

    let result = [];

    // let index = 0;
    // for(let i = mid; i<arr.length;i++) {
    //     result.push(arr[i]);
    // }

    result = arr.slice(mid);
    // console.log(result);
    return result;
}

function testBiggerHalf() {
    biggerHalf([4, 7, 2, 5]);
    biggerHalf([3, 19, 14, 7, 2, 19, 6]);
}

testBiggerHalf();