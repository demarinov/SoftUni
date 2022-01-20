function rotateArray(arr,num) {

    for(let i=1; i<=num;i++) {

        const el = arr.pop();
        arr.unshift(el);
    }

    console.log(arr.join(' '));
}

function testRotateArray() {
    rotateArray(['1', 
    '2', 
    '3', 
    '4'], 
    2
    );

    rotateArray(['Banana', 
    'Orange', 
    'Coconut', 
    'Apple'], 
    15
    );

}

testRotateArray();