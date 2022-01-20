function aggregateElements(arr) {

    function sumInverse(arr) {
        let result = 0;
        for(const el of arr) {
            result += 1/el;
        }
    
        console.log(result);
    }
    
    function sum(arr) {
        let result = 0;
        for(const el of arr) {
            result += el;
        }
    
        console.log(result);
    }
    
    function concat(arr) {
        let result = '';
        for(const el of arr) {
            result += el;
        }
    
        console.log(result);
    }

    sum(arr);
    sumInverse(arr);
    concat(arr);

}



function testAggregateElements() {
    aggregateElements([1, 2, 3]);
    aggregateElements([2, 4, 8, 16]);
}

testAggregateElements();