function getFibonator() {
    let state = [];
    return function fib() {

        if (state.length < 2) {
            state.push(1);
            return 1;
        } else {
            const stateVal = state[state.length-1] + state[state.length-2];
            state.push(stateVal);
            return stateVal;
        }
    }
}


function test() {
    let fib = getFibonator();
console.log(fib()); // 1
console.log(fib()); // 1
console.log(fib()); // 2
console.log(fib()); // 3
console.log(fib()); // 5
console.log(fib()); // 8
console.log(fib()); // 13

}

test();