function solution(input) {

    let num = 0;

    function add(val) {

        return num = input + val;
    }

    return add;
}

function test() {
    let add5 = solution(5);
console.log(add5(2));
console.log(add5(3));

let add7 = solution(7);
console.log(add7(2));
console.log(add7(3));

}

test();