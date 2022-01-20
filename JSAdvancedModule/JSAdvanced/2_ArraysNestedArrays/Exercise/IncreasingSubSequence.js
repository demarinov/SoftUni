function increasingSubSeq(arr) {


    let number=arr[0];
    let result = [];
    for(const el of arr) {
        if (el >= number) {
            result.push(el);
            number = el;
        }
    }

    return result;
}

function testIncreasingSubSeq() {
    increasingSubSeq([1, 
        3, 
        8, 
        4, 
        10, 
        12, 
        3, 
        2, 
        24]
        );

        increasingSubSeq([1, 
            2, 
            3,
            4]
            );
        increasingSubSeq([20, 
            3, 
            2, 
            15,
            6, 
            1]
            );    
}

testIncreasingSubSeq();